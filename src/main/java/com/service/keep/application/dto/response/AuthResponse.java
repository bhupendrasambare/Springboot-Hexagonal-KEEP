/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:40 am
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
public class AuthResponse {
    
    private UserResponse user;
    private TokenResponse token;

}
