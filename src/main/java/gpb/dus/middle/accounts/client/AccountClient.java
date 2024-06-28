package gpb.dus.middle.accounts.client;

import gpb.dus.middle.accounts.model.Account;
import gpb.dus.middle.accounts.model.CreateUserAccountV2;
import gpb.dus.middle.client.BaseClient;
import gpb.dus.middle.exception.model.ConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class AccountClient extends BaseClient {

    public static final String ACCOUNTS_API_PREFIX = "/v2/users";

    private final String baseUrl;

    protected AccountClient(@Value("${service.backend.url}") String backendUrl) {
        super(RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl(backendUrl + ACCOUNTS_API_PREFIX)
                .build());
        this.baseUrl = backendUrl + ACCOUNTS_API_PREFIX;
    }

    public List<Account> getUserAccounts(String tgUserId) {

        log.info("[AccountClient] отправлен запрос get {}", "/" + tgUserId + "/accounts");

        List<Account> accounts;

        try {
            accounts = getList("/" + tgUserId + "/accounts");
        } catch (Exception e) {
            throw new RuntimeException("Непредвиденная ошибка");
        }

        return accounts;
    }

    public void createAccount(String tgUserId, CreateUserAccountV2 createUserAccountV2) {

        log.info("[AccountClient] отправлен запрос post {} c телом {}",
                baseUrl + "/" + tgUserId + "/accounts",
                createUserAccountV2);

        ResponseEntity<Object> backendResponse = post("/" + tgUserId + "/accounts", createUserAccountV2);

        if (backendResponse.getStatusCode().equals(HttpStatusCode.valueOf(409))) {
            throw new ConflictException(HttpStatus.CONFLICT.getReasonPhrase());
        }

        if (backendResponse.getStatusCode().is4xxClientError() || backendResponse.getStatusCode().is5xxServerError()) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
