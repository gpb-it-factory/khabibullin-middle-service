package gpb.dus.middle.users.controller;


import gpb.dus.middle.api.generated.UsersResource;
import gpb.dus.middle.api.generated.model.CreateUserRequestV2Api;
import gpb.dus.middle.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UsersResource {

    private final UserService userService;

    @Override
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> createUserV2(CreateUserRequestV2Api createUserRequestV2Api) {
        log.info("[UserController] post {}", createUserRequestV2Api);

        userService.createUserV2(createUserRequestV2Api);

        return ResponseEntity.noContent().build();
    }
}
