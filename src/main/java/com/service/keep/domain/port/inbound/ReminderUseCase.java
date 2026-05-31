package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderUseCase {

    Reminder create(
            String userId,
            String noteId,
            LocalDateTime reminderTime,
            String title,
            String description
    );

    Reminder update(
            String userId,
            String reminderId,
            LocalDateTime reminderTime,
            String title,
            String description,
            Boolean completed
    );

    void markCompleted(
            String userId,
            String reminderId
    );

    void markIncomplete(
            String userId,
            String reminderId
    );

    Reminder getById(
            String userId,
            String reminderId
    );

    List<Reminder> getAllByUser(
            String userId
    );

    List<Reminder> getCompletedReminders(
            String userId
    );

    List<Reminder> getPendingReminders(
            String userId
    );

    List<Reminder> getPendingReminders();
}
