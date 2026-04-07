/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:50 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import org.apache.commons.lang3.StringUtils;

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
    private String tagId;

    private List<String> tags;
    private List<String> keywords;
    private String summary;

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
                String tagId,
                List<String> tags,
                List<String> keywords,
                String summary,
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
        this.tagId = tagId;

        this.tags = tags != null ? tags : new ArrayList<>();
        this.keywords = keywords != null ? keywords : new ArrayList<>();
        this.summary = summary;

        this.createdAt = createdAt;
        this.updatedAt = updatedAt == null ? createdAt : updatedAt;
    }

    public NoteId getId() { return id; }
    public UserId getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public boolean isPinned() { return pinned; }
    public boolean isArchived() { return archived; }
    public boolean isTrashed() { return trashed; }

    public String getReminder() { return reminder; }
    public String getTagId() { return tagId; }

    public List<String> getTags() { return tags; }
    public List<String> getKeywords() { return keywords; }
    public String getSummary() { return summary; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void update(String title, String description, String newTagIds) {
        this.title = title == null ? this.title : title;
        this.description = description == null ? this.description : description;

        if (newTagIds != null) this.tagId = newTagIds;

        this.updatedAt = LocalDateTime.now();
    }

    public void updateMetadata(List<String> tags, List<String> keywords, String summary) {
        if (tags != null) this.tags = tags;
        if (keywords != null) this.keywords = keywords;
        if (summary != null) this.summary = summary;

        this.updatedAt = LocalDateTime.now();
    }

    public void pin() {
        if (!this.pinned) {
            this.pinned = true;
            this.archived = false;
            this.trashed = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void unpin() {
        if (this.pinned) {
            this.pinned = false;
            this.archived = false;
            this.trashed = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void archive() {
        if (!this.archived) {
            this.archived = true;
            this.pinned = false;
            this.trashed = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void unarchive() {
        if (this.archived) {
            this.archived = false;
            this.pinned = false;
            this.trashed = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void moveToTrash() {
        if (!this.trashed) {
            this.archived = false;
            this.pinned = false;
            this.trashed = true;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void restoreFromTrash() {
        if (this.trashed) {
            this.archived = false;
            this.pinned = false;
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
        if (StringUtils.isNotBlank(tagId)) {
            this.tagId = tagId;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void removeTag(String tagId) {
        if (StringUtils.isNotBlank(tagId)) {
            this.tagId = null;
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
