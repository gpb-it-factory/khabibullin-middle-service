package gpb.dus.middle.users.service.impl;

import gpb.dus.middle.api.generated.model.CreateUserRequestV2Api;
import gpb.dus.middle.users.client.UserClient;
import gpb.dus.middle.users.mapper.UserMapper;
import gpb.dus.middle.users.model.CreateUserRequestV2;
import gpb.dus.middle.users.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final Validator validator;

    private final UserMapper userMapper;

    private final UserClient userClient;

    @Override
    public void createUserV2(CreateUserRequestV2Api createUserRequestV2Api) {
        log.info("[UserService] creating user {}", createUserRequestV2Api);

        CreateUserRequestV2 createUserRequestV2 = userMapper.toModel(createUserRequestV2Api);

        Set<ConstraintViolation<CreateUserRequestV2>> violations = validator.validate(createUserRequestV2);

        checkViolations(violations);

        userClient.register(createUserRequestV2);
    }

    private static void checkViolations(Set<ConstraintViolation<CreateUserRequestV2>> violations) {
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CreateUserRequestV2> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }
}
