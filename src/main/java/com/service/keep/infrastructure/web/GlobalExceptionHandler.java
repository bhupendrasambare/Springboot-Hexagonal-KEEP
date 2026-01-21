/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:19 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.web;

import com.service.keep.application.dto.response.ErrorResponse;
import com.service.keep.application.exception.ApplicationException;
import com.service.keep.application.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(
            ApplicationException ex,
            HttpServletRequest request
    ) {
        ErrorCode code = ex.getErrorCode();

        return ResponseEntity
                .status(code.status())
                .body(new ErrorResponse(
                        code.code(),
                        code.message(),
                        code.status().value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            Exception ex,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(ErrorCode.INTERNAL_ERROR.status())
                .body(new ErrorResponse(
                        ErrorCode.INTERNAL_ERROR.code(),
                        ErrorCode.INTERNAL_ERROR.message(),
                        ErrorCode.INTERNAL_ERROR.status().value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));
    }
}
