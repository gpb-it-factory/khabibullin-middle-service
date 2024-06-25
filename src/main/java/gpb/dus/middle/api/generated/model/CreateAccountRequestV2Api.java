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
 * Запрос на создание нового счёта (V2)
 */

@Schema(name = "CreateAccountRequestV2", description = "Запрос на создание нового счёта (V2)")
@JsonTypeName("CreateAccountRequestV2")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-29T01:00:08.524498054+03:00[Europe/Moscow]")
public class CreateAccountRequestV2Api {

  private String accountName;

  public CreateAccountRequestV2Api() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateAccountRequestV2Api(String accountName) {
    this.accountName = accountName;
  }

  public CreateAccountRequestV2Api accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  /**
   * Get accountName
   * @return accountName
  */
  @NotNull 
  @Schema(name = "accountName", example = "My first awesome account", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountName")
  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAccountRequestV2Api createAccountRequestV2 = (CreateAccountRequestV2Api) o;
    return Objects.equals(this.accountName, createAccountRequestV2.accountName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAccountRequestV2Api {\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
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

