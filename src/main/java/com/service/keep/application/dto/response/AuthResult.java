/**
 * author @bhupendrasambare
 * Date   :07/01/26
 * Time   :11:06 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResult {
    private UserResponse user;
    private TokenResponse token;
}
