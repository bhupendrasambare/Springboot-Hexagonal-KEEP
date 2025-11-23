/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:50 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Note {

    private final NoteId id;

    private final UserId userId;

    private String title;

    private String description;

    private Boolean pinned;

    private Boolean archived;

    private Boolean trash;

    private String reminder;

    private List<String> tagId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Note(NoteId id, UserId userId, String title, String description, Boolean pinned, Boolean archived, Boolean trash, String reminder, List<String> tagId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.pinned = pinned;
        this.archived = archived;
        this.trash = trash;
        this.reminder = reminder;
        this.tagId = tagId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
