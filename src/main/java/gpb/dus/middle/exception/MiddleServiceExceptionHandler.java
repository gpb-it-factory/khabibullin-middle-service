package gpb.dus.middle.exception;

import gpb.dus.middle.exception.model.ErrorV2;
import gpb.dus.middle.exception.model.UnprocessableEntity;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class MiddleServiceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorV2 handleException(Exception e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 500] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorV2 handleRuntimeException(final RuntimeException e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 500] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorV2 handleValidationException(final ValidationException e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 400] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorV2 handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 400] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorV2 handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 400] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorV2 handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 400] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                traceId
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorV2 handleMissingServletRequestParameterException(final UnprocessableEntity e) {
        UUID traceId = UUID.randomUUID();
        log.warn("[HTTP STATUS 409] {} with traceId {}", e.getMessage(), traceId, e);

        return makeErrorResponse(e,
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                traceId
        );
    }

    private ErrorV2 makeErrorResponse(Throwable e, String type, Integer code, UUID traceId) {

        return new ErrorV2(
                e.getMessage(),
                type,
                code.toString(),
                traceId
        );
    }
}
