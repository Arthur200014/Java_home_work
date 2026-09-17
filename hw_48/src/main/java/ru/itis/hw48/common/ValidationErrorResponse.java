package ru.itis.hw48.common;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse {

    private final String message;
    private final LocalDateTime timestamp;
    private final Map<String, String> errors;

    public ValidationErrorResponse(String message, LocalDateTime timestamp, Map<String, String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
