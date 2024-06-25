package gpb.dus.middle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class BaseClient {

    protected final RestTemplate rest;

    protected BaseClient(RestTemplate rest) {
        this.rest = rest;
    }

    protected ResponseEntity<Object> get(String path) {
        return makeAndSendRequest(HttpMethod.GET, path, null);
    }

    protected <T> List<T> getList(String path, Class<T> cls) {
        return makeAndSendRequestForList(path, cls);
    }

    protected <T> ResponseEntity<Object> post(String path, T body) {
        return makeAndSendRequest(HttpMethod.POST, path, body);
    }

    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method,
                                                          String path,
                                                          @Nullable T body) {
        HttpEntity<T> requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> backendServiceResponse;
        try {
            backendServiceResponse = rest.exchange(path, method, requestEntity, Object.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return manageMiddleServiceResponse(backendServiceResponse);
    }

    private <T> List<T> makeAndSendRequestForList(String path, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, cls);
        return rest.<List<T>>exchange(path, HttpMethod.GET, null, ParameterizedTypeReference.forType(type)).getBody();
    }


    public static <T> List<T> getForList(RestTemplate restTemplate, String url, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, cls);
        return restTemplate.<List<T>>exchange(url, HttpMethod.GET, null, ParameterizedTypeReference.forType(type))
                .getBody();
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    private ResponseEntity<Object> manageMiddleServiceResponse(ResponseEntity<Object> middleServiceResponse) {
        if (middleServiceResponse.getStatusCode().is2xxSuccessful()) {
            return middleServiceResponse;
        }

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(middleServiceResponse.getStatusCode());

        if (middleServiceResponse.hasBody()) {
            return responseBuilder.body(middleServiceResponse.getBody());
        }

        return responseBuilder.build();
    }
}
