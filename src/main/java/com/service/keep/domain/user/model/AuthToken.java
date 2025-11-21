/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:36 pm
 * Project:Keep
 **/
package com.service.keep.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String userId;

    private String username;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}
