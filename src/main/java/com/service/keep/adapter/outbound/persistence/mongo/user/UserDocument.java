/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:07 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.user;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@QueryEntity
@Getter
@Setter
@Document(collection = "users")
public class UserDocument {

    @Id
    private String id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
