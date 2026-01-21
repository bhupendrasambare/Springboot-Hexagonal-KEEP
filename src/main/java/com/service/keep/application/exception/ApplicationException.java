/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:16 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public abstract class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    protected ApplicationException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
