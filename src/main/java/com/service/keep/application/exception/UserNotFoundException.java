/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
