/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:55 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.TagsId;
import com.service.keep.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class Tags {

    private final TagsId id;
    private final UserId userId;
    private String color;
    private String imageUri;
    private String userName;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Tags(TagsId id,
                UserId userId,
                String color,
                String imageUri,
                String userName,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (userName == null || userName.isBlank()) throw new IllegalArgumentException("userName cannot be null/blank");
        if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");

        this.id = id;
        this.userId = userId;
        this.color = color;
        this.imageUri = imageUri;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt == null ? createdAt : updatedAt;
    }

    public TagsId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getColor() {
        return color;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) throw new IllegalArgumentException("newName cannot be null/blank");
        this.userName = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeColor(String newColor) {
        this.color = newColor;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateImageUri(String newImageUri) {
        this.imageUri = newImageUri;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tags tags = (Tags) o;
        return Objects.equals(id, tags.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id.getValue() +
                ", userId=" + userId.getValue() +
                ", userName='" + userName + '\'' +
                '}';
    }
}
