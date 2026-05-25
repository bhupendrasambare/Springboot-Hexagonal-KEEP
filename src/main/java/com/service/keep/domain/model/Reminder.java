/**
 * author @bhupendrasambare
 * Date   :10/05/26
 * Time   :11:10 pm
 * Project:Keep
 **/
package com.service.keep.domain.model;

import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.ReminderId;
import com.service.keep.domain.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reminder {

    private final ReminderId id;
    private final NoteId noteId;
    private final UserId userId;
    private LocalDateTime reminderTime;
    private String title;
    private String description;
    private boolean completed;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reminder(ReminderId reminderId, NoteId noteId, UserId userId, LocalDateTime reminderTime, String title, String description, boolean completed, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (reminderId == null) throw new IllegalArgumentException("id cannot be null");
        if (noteId == null) throw new IllegalArgumentException("note cannot be null");
        if (userId == null) throw new IllegalArgumentException("userId cannot be null");
        if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");

        this.id = reminderId;
        this.noteId = noteId;
        this.userId = userId;

        this.title = title == null ? "" : title;
        this.reminderTime = reminderTime == null ? null : reminderTime;
        this.description = description == null ? "" : description;

        this.completed = completed;

        this.createdAt = createdAt;
        this.updatedAt = updatedAt == null ? createdAt : updatedAt;
    }

    public ReminderId getId() {
        return id;
    }

    public NoteId getNoteId() {
        return noteId;
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

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return Objects.equals(id, reminder.id);
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", noteId=" + noteId +
                ", userId=" + userId +
                ", reminderTime=" + reminderTime +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
