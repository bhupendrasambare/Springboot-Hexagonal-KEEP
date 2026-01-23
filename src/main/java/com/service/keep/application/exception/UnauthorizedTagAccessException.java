/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class UnauthorizedTagAccessException extends ApplicationException {
    public UnauthorizedTagAccessException() {
        super(ErrorCode.TAG_UNAUTHORIZED);
    }
}
