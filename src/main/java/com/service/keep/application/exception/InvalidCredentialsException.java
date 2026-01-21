/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:17 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class InvalidCredentialsException extends ApplicationException {
    public InvalidCredentialsException() {
        super(ErrorCode.INVALID_CREDENTIALS);
    }
}
