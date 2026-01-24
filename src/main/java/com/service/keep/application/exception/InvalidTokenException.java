/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:17 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class InvalidTokenException extends ApplicationException {
    public InvalidTokenException() {
        super(ErrorCode.TOKEN_INVALID);
    }
}
