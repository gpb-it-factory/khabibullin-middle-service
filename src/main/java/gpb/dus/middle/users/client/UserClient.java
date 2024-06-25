package gpb.dus.middle.users.client;


import gpb.dus.middle.client.BaseClient;
import gpb.dus.middle.exception.model.ConflictException;
import gpb.dus.middle.exception.model.UnauthorizedException;
import gpb.dus.middle.users.model.CreateUserRequestV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class UserClient extends BaseClient {

    public static final String USERS_API_PREFIX = "/v2/users";

    private final String baseUrl;

    protected UserClient(@Value("${service.backend.url}") String backendUrl) {
        super(RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl(backendUrl + USERS_API_PREFIX)
                .build());
        this.baseUrl = backendUrl + USERS_API_PREFIX;
    }

    public void register(CreateUserRequestV2 createUserRequestV2) {

        log.info("[UserClient] отправлен запрос post {} c телом {}", baseUrl, createUserRequestV2);

        ResponseEntity<Object> backendResponse = post("", createUserRequestV2);

        if (backendResponse.getStatusCode().equals(HttpStatusCode.valueOf(409))) {
            throw new ConflictException(HttpStatus.CONFLICT.getReasonPhrase());
        }

        if (backendResponse.getStatusCode().is4xxClientError() || backendResponse.getStatusCode().is5xxServerError()) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void getUserMandatory(String tgUserId) {

        log.info("[UserClient] отправлен запрос get {}", baseUrl + "/" + tgUserId);

        ResponseEntity<Object> backendResponse = get("/" + tgUserId);

        if (!backendResponse.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
}
