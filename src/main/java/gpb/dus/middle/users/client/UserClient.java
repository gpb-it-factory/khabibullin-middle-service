package gpb.dus.middle.users.client;


import gpb.dus.middle.client.BaseClient;
import gpb.dus.middle.exception.model.UnprocessableEntity;
import gpb.dus.middle.users.model.CreateUserRequestV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class UserClient extends BaseClient {

    public static final String API_PREFIX = "/v2/users";

    protected UserClient(@Value("${service.backend.url}") String backendUrl,
                         RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(backendUrl + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build());
    }

    public void register(CreateUserRequestV2 createUserRequestV2) {

        ResponseEntity<Object> backendResponse = post("", createUserRequestV2);

        if (backendResponse.getStatusCode().equals(HttpStatusCode.valueOf(409))) {
            throw new UnprocessableEntity(HttpStatus.CONFLICT.getReasonPhrase());
        }

        if (backendResponse.getStatusCode().is4xxClientError() || backendResponse.getStatusCode().is5xxServerError()) {
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
