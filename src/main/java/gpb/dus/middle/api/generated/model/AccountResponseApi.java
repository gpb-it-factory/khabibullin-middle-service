package gpb.dus.middle.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Информация о счёте пользователя
 */

@Schema(name = "AccountResponse", description = "Информация о счёте пользователя")
@JsonTypeName("AccountResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-30T20:45:34.201097068+03:00[Europe/Moscow]")
public class AccountResponseApi {

  private UUID accountId;

  private String accountName;

  private BigDecimal amount;

  public AccountResponseApi() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountResponseApi(UUID accountId, String accountName, BigDecimal amount) {
    this.accountId = accountId;
    this.accountName = accountName;
    this.amount = amount;
  }

  public AccountResponseApi accountId(UUID accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
  */
  @NotNull @Valid 
  @Schema(name = "accountId", example = "52d2ef91-0b62-4d43-bb56-e7ec542ba8f8", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountId")
  public UUID getAccountId() {
    return accountId;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public AccountResponseApi accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  /**
   * Get accountName
   * @return accountName
  */
  @NotNull 
  @Schema(name = "accountName", example = "Деньги на шашлык", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountName")
  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public AccountResponseApi amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull @Valid 
  @Schema(name = "amount", example = "203605.20", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountResponseApi accountResponse = (AccountResponseApi) o;
    return Objects.equals(this.accountId, accountResponse.accountId) &&
        Objects.equals(this.accountName, accountResponse.accountName) &&
        Objects.equals(this.amount, accountResponse.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, accountName, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountResponseApi {\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

