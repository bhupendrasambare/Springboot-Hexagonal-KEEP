/**
 * author @bhupendrasambare
 * Date   :21/01/26
 * Time   :10:15 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        int status,
        String path,
        LocalDateTime timestamp
) {
}
