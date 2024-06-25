package gpb.dus.middle.accounts.client;

import gpb.dus.middle.accounts.model.Account;
import gpb.dus.middle.accounts.model.CreateUserAccountV2;
import gpb.dus.middle.client.BaseClient;
import gpb.dus.middle.exception.model.ConflictException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Service
public class AccountClient extends BaseClient {

    public static final String ACCOUNTS_API_PREFIX = "/v2/users";

    protected AccountClient(@Value("${service.backend.url}") String backendUrl,
                         RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(backendUrl + ACCOUNTS_API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build());
    }

    public List<Account> getUserAccounts(String tgUserId) {

        List<Account> accounts;

        try {
            accounts = getList("/" + tgUserId + "/accounts", Account.class);
        } catch (Exception e){
            throw new RuntimeException("Непредвиденная ошибка");
        }

        return accounts;
    }

    public void createAccount(String tgUserId, CreateUserAccountV2 createUserAccountV2) {

        ResponseEntity<Object> backendResponse = post("/" +  tgUserId + "/accounts", createUserAccountV2);

        if (backendResponse.getStatusCode().equals(HttpStatusCode.valueOf(409))) {
            throw new ConflictException(HttpStatus.CONFLICT.getReasonPhrase());
        }

        if (backendResponse.getStatusCode().is4xxClientError() || backendResponse.getStatusCode().is5xxServerError()) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
