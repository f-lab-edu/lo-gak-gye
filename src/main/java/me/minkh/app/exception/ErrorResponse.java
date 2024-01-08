package me.minkh.app.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private final int status;

    private final String message;

    private final List<Error> errors = new ArrayList<>();

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addError(String filed, String defaultMessage) {
        this.errors.add(new Error(filed, defaultMessage));
    }

    @Getter
    static class Error {

        private final String field;

        private final String defaultMessage;

        public Error(String field, String defaultMessage) {
            this.field = field;
            this.defaultMessage = defaultMessage;
        }
    }
}