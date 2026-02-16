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

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final UserId id;
    private String username;
    private String firstName;
    private String lastName;
    private Email email;
    private HashedPassword passwordHash;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(UserId id,
                String username,
                String firstName,
                String lastName,
                Email email,
                HashedPassword passwordHash,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (username == null || username.isBlank()) throw new IllegalArgumentException("username cannot be null/blank");
        if (email == null) throw new IllegalArgumentException("email cannot be null");
        if (passwordHash == null) throw new IllegalArgumentException("passwordHash cannot be null");
        if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt == null ? createdAt : updatedAt;
    }

    public UserId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Email getEmail() {
        return email;
    }

    public HashedPassword getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateProfile(String newUsername, String newFirstName, String newLastName) {
        if (newUsername != null && !newUsername.isBlank()) {
            this.username = newUsername;
        }
        if (newFirstName != null) {
            this.firstName = newFirstName;
        }
        if (newLastName != null) {
            this.lastName = newLastName;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void changePassword(HashedPassword newHashedPassword) {
        if (newHashedPassword == null) throw new IllegalArgumentException("newHashedPassword cannot be null");
        this.passwordHash = newHashedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id.getValue() +
                ", username='" + username + '\'' +
                ", email=" + email.getValue() +
                '}';
    }

    public static User createGoogleUser(
            UserId userId,
            Email email,
            String username
    ) {
        return new User(
                userId,
                username,
                username,
                null,
                email,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}