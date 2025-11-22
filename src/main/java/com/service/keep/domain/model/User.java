/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:35 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private Email email;

    private HashedPassword passwordHash;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
