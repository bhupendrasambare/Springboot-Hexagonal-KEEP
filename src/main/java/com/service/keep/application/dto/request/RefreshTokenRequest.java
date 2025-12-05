/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenRequest {

    @NotBlank
    String refreshToken;

}
