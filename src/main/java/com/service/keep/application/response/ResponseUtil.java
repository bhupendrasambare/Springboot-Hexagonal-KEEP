/**
 * author @bhupendrasambare
 * Date   :27/03/26
 * Time   :12:27 am
 * Project:Keep
 **/
package com.service.keep.application.response;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Response> success(String message) {
        return ResponseEntity.ok(
                Response.builder()
                        .status(true)
                        .message(message)
                        .data(null)
                        .errorCode(null)
                        .build()
        );
    }

    public static ResponseEntity<Response> success(String message, Object data) {
        return ResponseEntity.ok(
                Response.builder()
                        .status(true)
                        .message(message)
                        .data(data)
                        .errorCode(null)
                        .build()
        );
    }
}
