/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:17 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class TokenExpiredException extends ApplicationException {
    public TokenExpiredException() {
        super(ErrorCode.TOKEN_EXPIRED);
    }
}
