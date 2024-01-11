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

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

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

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateExceptionHandler(IllegalStateException e) {
        log.error("illegalStateExceptionHandler", e);
        return this.exceptionHandler(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
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
