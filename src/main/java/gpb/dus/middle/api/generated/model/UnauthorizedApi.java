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
 * Пользователь не найден
 */

@Schema(name = "Unauthorized", description = "Пользователь не найден")
@JsonTypeName("Unauthorized")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-26T16:56:00.702950276+03:00[Europe/Moscow]")
public class UnauthorizedApi {

  private JsonNullable<Object> message = JsonNullable.<Object>undefined();

  private JsonNullable<Object> type = JsonNullable.<Object>undefined();

  private JsonNullable<Object> code = JsonNullable.<Object>undefined();

  private JsonNullable<Object> traceId = JsonNullable.<Object>undefined();

  public UnauthorizedApi() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UnauthorizedApi(Object message, Object type, Object code, Object traceId) {
    this.message = JsonNullable.of(message);
    this.type = JsonNullable.of(type);
    this.code = JsonNullable.of(code);
    this.traceId = JsonNullable.of(traceId);
  }

  public UnauthorizedApi message(Object message) {
    this.message = JsonNullable.of(message);
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull 
  @Schema(name = "message", example = "Произошло что-то ужасное, но станет лучше, честно", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public JsonNullable<Object> getMessage() {
    return message;
  }

  public void setMessage(JsonNullable<Object> message) {
    this.message = message;
  }

  public UnauthorizedApi type(Object type) {
    this.type = JsonNullable.of(type);
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", example = "GeneralError", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public JsonNullable<Object> getType() {
    return type;
  }

  public void setType(JsonNullable<Object> type) {
    this.type = type;
  }

  public UnauthorizedApi code(Object code) {
    this.code = JsonNullable.of(code);
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @NotNull 
  @Schema(name = "code", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public JsonNullable<Object> getCode() {
    return code;
  }

  public void setCode(JsonNullable<Object> code) {
    this.code = code;
  }

  public UnauthorizedApi traceId(Object traceId) {
    this.traceId = JsonNullable.of(traceId);
    return this;
  }

  /**
   * Get traceId
   * @return traceId
  */
  @NotNull 
  @Schema(name = "traceId", example = "5f59e024-03c7-498d-9fc9-b8b15fd49c47", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("traceId")
  public JsonNullable<Object> getTraceId() {
    return traceId;
  }

  public void setTraceId(JsonNullable<Object> traceId) {
    this.traceId = traceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnauthorizedApi unauthorized = (UnauthorizedApi) o;
    return Objects.equals(this.message, unauthorized.message) &&
        Objects.equals(this.type, unauthorized.type) &&
        Objects.equals(this.code, unauthorized.code) &&
        Objects.equals(this.traceId, unauthorized.traceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, type, code, traceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnauthorizedApi {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
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

