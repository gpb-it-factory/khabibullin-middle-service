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
 * Формат непредвиденной ошибки (V2)
 */

@Schema(name = "ErrorV2", description = "Формат непредвиденной ошибки (V2)")
@JsonTypeName("ErrorV2")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-23T18:03:21.394341351+03:00[Europe/Moscow]")
public class ErrorV2Api {

    private String message;

    private String type;

    private String code;

    private UUID traceId;

    public ErrorV2Api() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ErrorV2Api(String message, String type, String code, UUID traceId) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.traceId = traceId;
    }

    public ErrorV2Api message(String message) {
        this.message = message;
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
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorV2Api type(String type) {
        this.type = type;
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ErrorV2Api code(String code) {
        this.code = code;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorV2Api traceId(UUID traceId) {
        this.traceId = traceId;
        return this;
    }

    /**
     * Get traceId
     *
     * @return traceId
     */
    @NotNull
    @Valid
    @Schema(name = "traceId", example = "5f59e024-03c7-498d-9fc9-b8b15fd49c47", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("traceId")
    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
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
        ErrorV2Api errorV2 = (ErrorV2Api) o;
        return Objects.equals(this.message, errorV2.message) &&
                Objects.equals(this.type, errorV2.type) &&
                Objects.equals(this.code, errorV2.code) &&
                Objects.equals(this.traceId, errorV2.traceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, type, code, traceId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorV2Api {\n");
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

