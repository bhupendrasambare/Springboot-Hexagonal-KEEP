/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.application.exception;

public class UnauthorizedNoteAccessException extends ApplicationException {
    public UnauthorizedNoteAccessException() {
        super(ErrorCode.NOTE_UNAUTHORIZED);
    }
}
