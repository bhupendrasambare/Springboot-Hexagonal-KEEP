/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:10 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProfileRequest {

    private String userName;

    private String firstName;

    private String lastName;

}
