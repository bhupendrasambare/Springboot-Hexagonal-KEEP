/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:36 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public final class AuthToken {

    private final String token;
    private final UserId userId;
    private final String username;
    private boolean expired;
    private final LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public AuthToken(String token,
                     UserId userId,
                     String username,
                     LocalDateTime createdAt,
                     LocalDateTime expiresAt) {
        if (token == null || token.isBlank()) throw new IllegalArgumentException("token cannot be null or blank");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");
        if (expiresAt == null) throw new IllegalArgumentException("expiresAt cannot be null");

        this.token = token;
        this.userId = userId;
        this.username = username;
        this.expired = false;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isExpired() {
        if (expired) return true;
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void markExpired() {
        this.expired = true;
    }

    public void extendExpiry(LocalDateTime newExpiry) {
        if (newExpiry == null) throw new IllegalArgumentException("newExpiry cannot be null");
        if (!newExpiry.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("newExpiry must be in future");
        this.expiresAt = newExpiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthToken authToken = (AuthToken) o;
        return Objects.equals(token, authToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "token='[PROTECTED]'" +
                ", userId=" + userId.getValue() +
                ", username='" + username + '\'' +
                ", expired=" + expired +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                '}';
    }
}

