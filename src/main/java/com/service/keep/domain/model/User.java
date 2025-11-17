/**
 * author @bhupendrasambare
 * Date   :17/11/25
 * Time   :10:38 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String passwordHash;
    private String username;
    private String email;
    private String profileImage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean active = true;
    private boolean emailVerified = false;

}
