/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:16 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Auth
    INVALID_CREDENTIALS("AUTH_001", "Invalid credentials", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH_002", "Token expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID("AUTH_003", "Invalid token", HttpStatus.UNAUTHORIZED),

    // User
    USER_NOT_FOUND("USER_001", "User not found", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("USER_002", "Email already registered", HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXISTS("USER_003", "Username already registered", HttpStatus.CONFLICT),

    // Notes
    NOTE_NOT_FOUND("NOTE_001", "Note not found", HttpStatus.NOT_FOUND),
    NOTE_UNAUTHORIZED("NOTE_002", "Unauthorized note access", HttpStatus.FORBIDDEN),

    // Tags
    TAG_NOT_FOUND("TAG_001", "Tag not found", HttpStatus.NOT_FOUND),
    TAG_UNAUTHORIZED("TAG_002", "Unauthorized tag access", HttpStatus.FORBIDDEN),

    // Validation
    VALIDATION_ERROR("VAL_001", "Validation error", HttpStatus.BAD_REQUEST),

    // Generic
    INTERNAL_ERROR("GEN_001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String code() { return code; }
    public String message() { return message; }
    public HttpStatus status() { return status; }
}
