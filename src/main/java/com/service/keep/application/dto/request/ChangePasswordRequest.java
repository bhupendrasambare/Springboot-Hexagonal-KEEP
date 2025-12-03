/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:13 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {

    private String oldPassword;

    private String newPassword;

}
