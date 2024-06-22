package gpb.dus.middle.api.generated.model;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;

import jakarta.annotation.Generated;

/**
 * Запрос на создание нового пользователя (V2)
 */

@Schema(name = "CreateUserRequestV2", description = "Запрос на создание нового пользователя (V2)")
@JsonTypeName("CreateUserRequestV2")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-23T18:03:21.394341351+03:00[Europe/Moscow]")
public class CreateUserRequestV2Api {

    private Long userId;

    private String userName;

    public CreateUserRequestV2Api() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public CreateUserRequestV2Api(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public CreateUserRequestV2Api userId(Long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Идентификатор пользователя в Telegram
     *
     * @return userId
     */
    @NotNull
    @Schema(name = "userId", example = "348741706", description = "Идентификатор пользователя в Telegram", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CreateUserRequestV2Api userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Username пользователя в Telegram
     *
     * @return userName
     */
    @NotNull
    @Size(min = 3)
    @Schema(name = "userName", example = "vrvaganov", description = "Username пользователя в Telegram", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUserRequestV2Api createUserRequestV2 = (CreateUserRequestV2Api) o;
        return Objects.equals(this.userId, createUserRequestV2.userId) &&
                Objects.equals(this.userName, createUserRequestV2.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateUserRequestV2Api {\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

