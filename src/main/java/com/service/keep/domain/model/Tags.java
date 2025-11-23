/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:55 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.TagsId;
import com.service.keep.domain.valueobject.UserId;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;


@Setter
@Getter
public class Tags {

    private final TagsId id;

    private final UserId userId;

    private String color;

    private String imageUri;

    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Tags(TagsId id, UserId userId, String color, String imageUri, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.color = color;
        this.imageUri = imageUri;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
