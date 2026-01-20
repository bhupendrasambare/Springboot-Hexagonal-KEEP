/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:08 pm
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
public class UserResponse {

    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
