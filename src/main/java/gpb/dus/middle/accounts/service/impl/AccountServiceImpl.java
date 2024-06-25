package gpb.dus.middle.accounts.service.impl;

import gpb.dus.middle.accounts.client.AccountClient;
import gpb.dus.middle.accounts.mapper.AccountMapper;
import gpb.dus.middle.accounts.service.AccountService;
import gpb.dus.middle.api.generated.model.CreateAccountRequestV2Api;
import gpb.dus.middle.exception.model.ConflictException;
import gpb.dus.middle.users.client.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountClient accountClient;

    private final UserClient userClient;

    private final AccountMapper accountMapper;

    @Override
    public void createUserAccount(String tgUserId, CreateAccountRequestV2Api createAccountRequestV2Api) {

        userClient.getUserMandatory(tgUserId);

        if(!accountClient.getUserAccounts(tgUserId).isEmpty()){
            throw new ConflictException("Такой счет у данного пользователя уже есть");
        }

        accountClient.createAccount(tgUserId, accountMapper.toModel(createAccountRequestV2Api));
    }
}
