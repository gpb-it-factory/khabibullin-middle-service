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
 * Некорректные параметры запроса
 */

@Schema(name = "BadRequest", description = "Некорректные параметры запроса")
@JsonTypeName("BadRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-23T18:03:21.394341351+03:00[Europe/Moscow]")
public class BadRequestApi {

    private JsonNullable<Object> message = JsonNullable.<Object>undefined();

    private JsonNullable<Object> type = JsonNullable.<Object>undefined();

    private JsonNullable<Object> code = JsonNullable.<Object>undefined();

    private JsonNullable<Object> traceId = JsonNullable.<Object>undefined();

    public BadRequestApi() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public BadRequestApi(Object message, Object type, Object code, Object traceId) {
        this.message = JsonNullable.of(message);
        this.type = JsonNullable.of(type);
        this.code = JsonNullable.of(code);
        this.traceId = JsonNullable.of(traceId);
    }

    public BadRequestApi message(Object message) {
        this.message = JsonNullable.of(message);
        return this;
    }

    /**
     * Get message
     *
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

    public BadRequestApi type(Object type) {
        this.type = JsonNullable.of(type);
        return this;
    }

    /**
     * Get type
     *
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

    public BadRequestApi code(Object code) {
        this.code = JsonNullable.of(code);
        return this;
    }

    /**
     * Get code
     *
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

    public BadRequestApi traceId(Object traceId) {
        this.traceId = JsonNullable.of(traceId);
        return this;
    }

    /**
     * Get traceId
     *
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
        BadRequestApi badRequest = (BadRequestApi) o;
        return Objects.equals(this.message, badRequest.message) &&
                Objects.equals(this.type, badRequest.type) &&
                Objects.equals(this.code, badRequest.code) &&
                Objects.equals(this.traceId, badRequest.traceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, type, code, traceId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BadRequestApi {\n");
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

