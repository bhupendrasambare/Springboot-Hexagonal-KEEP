/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:08 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
