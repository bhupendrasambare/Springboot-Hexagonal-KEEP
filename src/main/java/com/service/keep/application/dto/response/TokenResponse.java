/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :11:04 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.Builder;

@Builder
public class TokenResponse {

    private String accessToken;

    private String refreshToken;
}
