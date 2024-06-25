package gpb.dus.middle.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Информация о пользователе (V2)
 */

@Schema(name = "UserResponseV2", description = "Информация о пользователе (V2)")
@JsonTypeName("UserResponseV2")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-29T01:00:08.524498054+03:00[Europe/Moscow]")
public class UserResponseV2Api {

  private UUID userId;

  public UserResponseV2Api() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserResponseV2Api(UUID userId) {
    this.userId = userId;
  }

  public UserResponseV2Api userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Идентификатор пользователя в backend-сервисе
   * @return userId
  */
  @NotNull @Valid 
  @Schema(name = "userId", example = "2d7b7a7a-680e-422e-9cc3-23c68e2ff398", description = "Идентификатор пользователя в backend-сервисе", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponseV2Api userResponseV2 = (UserResponseV2Api) o;
    return Objects.equals(this.userId, userResponseV2.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponseV2Api {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

