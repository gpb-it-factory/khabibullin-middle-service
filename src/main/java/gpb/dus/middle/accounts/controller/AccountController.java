package gpb.dus.middle.accounts.controller;

import gpb.dus.middle.accounts.service.AccountService;
import gpb.dus.middle.api.generated.AccountsResource;
import gpb.dus.middle.api.generated.model.AccountResponseApi;
import gpb.dus.middle.api.generated.model.CreateAccountRequestV2Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountsResource {

    private final AccountService accountService;

    @Override
    public ResponseEntity<Void> createUserAccountV2(Long id, CreateAccountRequestV2Api createAccountRequestV2Api) {
        log.info("[Account controller] post {}", createAccountRequestV2Api);

        accountService.createUserAccount(id.toString(), createAccountRequestV2Api);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AccountResponseApi> getUserAccountBalance(Long id) {
        log.info("[Account controller] get user = {} account balance", id.toString());

        return new ResponseEntity<>( accountService.getUserAccountBalance(id.toString()), HttpStatusCode.valueOf(200));
    }

}
