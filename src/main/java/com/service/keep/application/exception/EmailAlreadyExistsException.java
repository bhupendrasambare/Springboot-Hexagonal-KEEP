/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class EmailAlreadyExistsException extends ApplicationException {
    public EmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
