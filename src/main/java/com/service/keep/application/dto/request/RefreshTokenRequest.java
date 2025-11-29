/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RefreshTokenRequest {

    String refreshToken;

}
