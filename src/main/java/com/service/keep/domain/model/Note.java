/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:50 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Note {

    private final NoteId id;
    private final UserId userId;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;
    private String reminder;
    private final List<String> tagId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Note(NoteId id,
                UserId userId,
                String title,
                String description,
                boolean pinned,
                boolean archived,
                boolean trashed,
                String reminder,
                List<String> tagId,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");

        this.id = id;
        this.userId = userId;
        this.title = title == null ? "" : title;
        this.description = description == null ? "" : description;
        this.pinned = pinned;
        this.archived = archived;
        this.trashed = trashed;
        this.reminder = reminder;
        this.tagId = tagId == null ? new ArrayList<>() : new ArrayList<>(tagId);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt == null ? createdAt : updatedAt;
    }

    public NoteId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPinned() {
        return pinned;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public String getReminder() {
        return reminder;
    }

    public List<String> getTagId() {
        return new ArrayList<>(tagId);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void update(String title, String description, List<String> newTagIds) {
        this.title = title == null ? this.title : title;
        this.description = description == null ? this.description : description;
        this.tagId.clear();
        if (newTagIds != null) this.tagId.addAll(newTagIds);
        this.updatedAt = LocalDateTime.now();
    }

    public void pin() {
        if (!this.pinned) {
            this.pinned = true;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void unpin() {
        if (this.pinned) {
            this.pinned = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void archive() {
        if (!this.archived) {
            this.archived = true;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void unarchive() {
        if (this.archived) {
            this.archived = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void moveToTrash() {
        if (!this.trashed) {
            this.trashed = true;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void restoreFromTrash() {
        if (this.trashed) {
            this.trashed = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
        this.updatedAt = LocalDateTime.now();
    }

    public void clearReminder() {
        this.reminder = null;
        this.updatedAt = LocalDateTime.now();
    }

    public void addTag(String tagId) {
        if (tagId != null && !this.tagId.contains(tagId)) {
            this.tagId.add(tagId);
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void removeTag(String tagId) {
        if (tagId != null && this.tagId.remove(tagId)) {
            this.updatedAt = LocalDateTime.now();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id.getValue() +
                ", userId=" + userId.getValue() +
                ", title='" + title + '\'' +
                ", pinned=" + pinned +
                ", archived=" + archived +
                ", trashed=" + trashed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
