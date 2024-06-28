package gpb.dus.middle.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.Body;
import gpb.dus.middle.api.generated.model.CreateUserRequestV2Api;
import gpb.dus.middle.exception.model.ErrorV2;
import gpb.dus.middle.users.client.UserClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static gpb.dus.middle.users.client.UserClient.USERS_API_PREFIX;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Environment environment;

    private static WireMockServer wireMockServer;
    private CreateUserRequestV2Api createUserRequestV2Api;
    private CreateUserRequestV2Api createUserRequestV2ApiWithInvalidName;


    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(7777));
        configureFor("localhost", 7777);
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void beforeEach() {
        createUserRequestV2Api = new CreateUserRequestV2Api(
                348741706L,
                "test_name"
        );

        createUserRequestV2ApiWithInvalidName = new CreateUserRequestV2Api(
                348741706L,
                "t"
        );
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Should return no content when correct 'CreateUserRequestV2Api' provided")
    @Test
    void When_Correct_User_Register_Than_No_Content_Answer() throws Exception {

            stubFor(post(urlEqualTo(USERS_API_PREFIX))
                    .willReturn(aResponse().withStatus(HttpStatus.NO_CONTENT.value())));

            //TODO Это костыль иначе broken pipe при первом вызове при прогоне тестов. Не могу придумать как починить иначе
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(createUserRequestV2Api)));

            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(createUserRequestV2Api)))
                    .andExpectAll(
                            status().isNoContent()
                    );
    }

    @DisplayName("Should return validation error when not valid 'CreateUserRequestV2Api' provided")
    @Test
    void When_Not_Valid_User_Register_Than_BadRequest_Answer() throws Exception {

        String testMessage = String.format(
                "[Field error in object 'createUserRequestV2Api' on field 'userName': rejected value [%s]",
                createUserRequestV2ApiWithInvalidName.getUserName()
        );
        String testType = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String testCode = String.valueOf(HttpStatus.BAD_REQUEST.value());

        ErrorV2 testError = new ErrorV2(
                testMessage,
                testType,
                testCode,
                UUID.randomUUID()
        );

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestV2ApiWithInvalidName)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(testError.message())))
                .andExpect(jsonPath("$.type").value(testType))
                .andExpect(jsonPath("$.code").value(testCode));
    }

    @DisplayName("Should return conflict error when conflict answer from backend")
    @Test
    void When_Conflict_User_Register_Than_Conflict_Answer() throws Exception {

        String testMessage = "Conflict";
        String testType = HttpStatus.CONFLICT.getReasonPhrase();
        String testCode = String.valueOf(HttpStatus.CONFLICT.value());

        ErrorV2 testError = new ErrorV2(
                testMessage,
                testType,
                testCode,
                UUID.randomUUID()
        );

        stubFor(post(urlEqualTo(USERS_API_PREFIX))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.CONFLICT.value())
                        .withResponseBody(new Body(asJsonString(testError)))
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestV2Api)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", containsString(testError.message())))
                .andExpect(jsonPath("$.type").value(testType))
                .andExpect(jsonPath("$.code").value(testCode));
    }

    @DisplayName("Should return internal server error when internal server error answer from backend")
    @Test
    void When_Internal_Server_Error_User_Register_Than_Internal_Server_Error_Answer() throws Exception {

        String testMessage = "Internal Server Error";
        String testType = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String testCode = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ErrorV2 testError = new ErrorV2(
                testMessage,
                testType,
                testCode,
                UUID.randomUUID()
        );

        stubFor(post(urlEqualTo(USERS_API_PREFIX))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withResponseBody(new Body(asJsonString(testError)))
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestV2Api)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", containsString(testError.message())))
                .andExpect(jsonPath("$.type").value(testType))
                .andExpect(jsonPath("$.code").value(testCode));
    }

    @DisplayName("Should return internal server error when 4xx answer from backend")
    @Test
    void When_4xx_response_code_User_Register_Than_Internal_Server_Error_Answer() throws Exception {

        String testMessage = "Internal Server Error";
        String testType = HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase();
        String testCode = String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value());

        ErrorV2 testError = new ErrorV2(
                testMessage,
                testType,
                testCode,
                UUID.randomUUID()
        );

        stubFor(post(urlEqualTo(USERS_API_PREFIX))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .withResponseBody(new Body(asJsonString(testError)))
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequestV2Api)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", containsString("Internal Server Error")))
                .andExpect(jsonPath("$.type").value(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()))
                .andExpect(jsonPath("$.code").value(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }
}