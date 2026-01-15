/**
 * author @bhupendrasambare
 * Date   :15/01/26
 * Time   :11:02 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.auth;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "auth_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenDocument {

    @Id
    private String token;

    @Indexed
    private String userId;

    private String username;

    private boolean expired;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}
