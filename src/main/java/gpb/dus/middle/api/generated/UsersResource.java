/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package gpb.dus.middle.api.generated;

import gpb.dus.middle.api.generated.model.BadRequestApi;
import gpb.dus.middle.api.generated.model.ConflictApi;
import gpb.dus.middle.api.generated.model.CreateUserRequestV2Api;
import gpb.dus.middle.api.generated.model.ErrorV2Api;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-26T16:56:00.702950276+03:00[Europe/Moscow]")
@Validated
@Tag(name = "users", description = "Операции с пользователями")
public interface UsersResource {

    /**
     * POST /middle/v2/users : Создать нового пользователя (v2)
     *
     * @param createUserRequestV2Api  (required)
     * @return Пользователь создан (status code 204)
     *         or Некорректный запрос (status code 400)
     *         or Пользователь уже зарегистрирован (status code 409)
     *         or Непредвиденная ошибка (status code 200)
     */
    @Operation(
        operationId = "createUserV2",
        summary = "Создать нового пользователя (v2)",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestApi.class))
            }),
            @ApiResponse(responseCode = "409", description = "Пользователь уже зарегистрирован", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictApi.class))
            }),
            @ApiResponse(responseCode = "default", description = "Непредвиденная ошибка", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorV2Api.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/middle/v2/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<Void> createUserV2(
        @Parameter(name = "CreateUserRequestV2Api", description = "", required = true) @Valid @RequestBody CreateUserRequestV2Api createUserRequestV2Api
    );

}
