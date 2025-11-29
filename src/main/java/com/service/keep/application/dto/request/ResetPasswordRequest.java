/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :11:25 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordRequest {

    private String token;

    private String newPassword;

    private String oldPassword;

}
