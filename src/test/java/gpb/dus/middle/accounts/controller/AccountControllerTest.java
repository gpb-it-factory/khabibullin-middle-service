package gpb.dus.middle.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import gpb.dus.middle.accounts.model.Account;
import gpb.dus.middle.api.generated.model.CreateAccountRequestV2Api;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static gpb.dus.middle.accounts.client.AccountClient.ACCOUNTS_API_PREFIX;
import static gpb.dus.middle.users.client.UserClient.USERS_API_PREFIX;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Environment environment;

    private static WireMockServer wireMockServer;

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

    @DisplayName("Should return no content when correct 'CreateAccountRequestV2Api' provided")
    @Test
    void When_Correct_Create_Account_Request_Than_No_Content_Answer() throws Exception {

        String tgUserId = "123";
        String accountName = "test_name";

        CreateAccountRequestV2Api testCreateAccountRequest = new CreateAccountRequestV2Api(accountName);

        stubFor(get(urlEqualTo(USERS_API_PREFIX + "/" + tgUserId))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        stubFor(get(urlEqualTo(ACCOUNTS_API_PREFIX + "/" + tgUserId + "/accounts"))
                .willReturn(okJson(asJsonString(Collections.emptyList()))));

        stubFor(post(urlEqualTo(ACCOUNTS_API_PREFIX + "/" + tgUserId + "/accounts"))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users/" + tgUserId + "/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testCreateAccountRequest)))
                .andExpectAll(
                        status().isNoContent()
                );
    }


    @DisplayName("Should return error when user is not registered")
    @Test
    void When_User_Is_Not_Registered_Than_Unathorized_answer() throws Exception {

        String tgUserId = "123";
        String accountName = "test_name";

        CreateAccountRequestV2Api testCreateAccountRequest = new CreateAccountRequestV2Api(accountName);

        stubFor(get(urlEqualTo(USERS_API_PREFIX + "/" + tgUserId))
                .willReturn(aResponse().withStatus(HttpStatus.UNAUTHORIZED.value())));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users/" + tgUserId + "/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testCreateAccountRequest)))
                .andExpectAll(
                        status().isUnauthorized()
                );
    }

    @DisplayName("Should return error when user already have account")
    @Test
    void When_Already_Has_Account_Than_Conflict_Answer() throws Exception {

        String tgUserId = "123";
        String testAccountName = "test_name";

        Account testAccount = new Account("111", testAccountName, "5000");

        CreateAccountRequestV2Api testCreateAccountRequest = new CreateAccountRequestV2Api(testAccountName);

        stubFor(get(urlEqualTo(USERS_API_PREFIX + "/" + tgUserId))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        stubFor(get(urlEqualTo(ACCOUNTS_API_PREFIX + "/" + tgUserId + "/accounts"))
                .willReturn(okJson(asJsonString(List.of(testAccount)))));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:7777/middle/v2/users/" + tgUserId + "/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testCreateAccountRequest)))
                .andExpectAll(
                        status().isConflict()
                );
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}