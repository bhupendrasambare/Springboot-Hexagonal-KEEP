/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:35 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private final UserId id;

    private String username;

    private String firstName;

    private String lastName;

    private Email email;

    private HashedPassword passwordHash;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public User(UserId id, String username, String firstName, String lastName, Email email, HashedPassword passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
