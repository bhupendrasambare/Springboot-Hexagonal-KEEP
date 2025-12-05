/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :11:20 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordRequest {

    @NotBlank
    @Email
    private String email;

}
