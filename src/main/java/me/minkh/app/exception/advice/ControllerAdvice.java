package me.minkh.app.exception.advice;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.exception.CharacterNotFoundException;
import me.minkh.app.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeExceptionHandler", e);
        return this.exceptionHandler(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("illegalArgumentExceptionHandler", e);
        return this.exceptionHandler(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler", e);
        return this.exceptionHandler(e, HttpStatus.BAD_REQUEST, "Method Argument Not Valid");
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<ErrorResponse> characterNotFoundExceptionHandler(CharacterNotFoundException e) {
        log.error("characterNotFoundExceptionHandler", e);
        return this.exceptionHandler(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> httpClientErrorExceptionHandler(HttpClientErrorException e) {
        log.error("httpClientErrorException", e);
        HttpStatus httpStatus = (HttpStatus) e.getStatusCode();
        // TODO: 이후에 message 처리 방법을 고려해 봐야 한다.
        if (httpStatus == HttpStatus.UNAUTHORIZED) {
            return this.exceptionHandler(e, httpStatus, "유효하지 않은 API키 입니다.");
        }
        return this.exceptionHandler(e, httpStatus, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> exceptionHandler(Exception e, HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
        if (e instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) e).getAllErrors().forEach(error -> {
                String field = ((FieldError) error).getField();
                String defaultMessage = error.getDefaultMessage();
                errorResponse.addError(field, defaultMessage);
            });
        }
        return new ResponseEntity<>(errorResponse, status);
    }
}
