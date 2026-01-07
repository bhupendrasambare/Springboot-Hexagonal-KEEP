/**
 * author @bhupendrasambare
 * Date   :07/01/26
 * Time   :11:06 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResult {
    private UserResponse user;
    private TokenResponse token;
}
