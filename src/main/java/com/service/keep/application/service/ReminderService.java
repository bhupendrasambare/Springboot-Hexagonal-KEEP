/**
 * author @bhupendrasambare
 * Date   :14/05/26
 * Time   :11:42 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.exception.NoteNotFoundException;
import com.service.keep.application.exception.UserNotFoundException;
import com.service.keep.domain.model.Note;
import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.port.outbound.ReminderRepositoryPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.ReminderId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service implementation responsible for managing reminders.
 *
 * Handles:
 * - Reminder creation
 * - Reminder updates
 * - Reminder completion status
 * - Reminder ownership validation
 * - Reminder retrieval
 */
@Service
@RequiredArgsConstructor
public class ReminderService implements ReminderUseCase {

    private final ReminderRepositoryPort reminderRepository;
    private final UserRepositoryPort userRepository;
    private final NoteRepositoryPort noteRepository;

    /**
     * Creates a new reminder for a note.
     *
     * Validation:
     * - User must exist
     * - Note must exist
     * - Note must belong to the user
     */
    @Override
    public Reminder create(
            String userId,
            String noteId,
            LocalDateTime reminderTime,
            String title,
            String description
    ) {

        validateUser(userId);

        Note note = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(NoteNotFoundException::new);

        validateNoteOwnership(userId, note);

        Reminder reminder = new Reminder(
                new ReminderId(UUID.randomUUID().toString()),
                new NoteId(noteId),
                new UserId(userId),
                reminderTime,
                title,
                description,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return reminderRepository.save(reminder);
    }

    /**
     * Updates an existing reminder.
     *
     * Only non-null/non-empty fields are updated.
     */
    @Override
    public Reminder update(
            String userId,
            String reminderId,
            LocalDateTime reminderTime,
            String title,
            String description,
            Boolean completed
    ) {

        Reminder reminder = getOwnedReminder(userId, reminderId);

        if (ObjectUtils.isNotEmpty(reminderTime)) {
            reminder.setReminderTime(reminderTime);
        }

        if (StringUtils.isNotBlank(title)) {
            reminder.setTitle(title);
        }

        if (StringUtils.isNotBlank(description)) {
            reminder.setDescription(description);
        }

        if (completed != null) {
            reminder.setCompleted(completed);
        }

        reminder.setUpdatedAt(LocalDateTime.now());

        return reminderRepository.save(reminder);
    }

    /**
     * Marks a reminder as completed by the owner.
     */
    @Override
    public void markCompleted(String userId, String reminderId) {

        Reminder reminder = getOwnedReminder(userId, reminderId);

        reminder.setCompleted(true);
        reminder.setUpdatedAt(LocalDateTime.now());

        reminderRepository.save(reminder);
    }

    /**
     * Marks a reminder as completed by the scheduler/system.
     *
     * No ownership validation is required because
     * this method is used internally.
     */
    @Override
    public void markCompletedBySystem(String reminderId) {

        Reminder reminder = getOwnedReminder(reminderId);

        reminder.setCompleted(true);
        reminder.setUpdatedAt(LocalDateTime.now());

        reminderRepository.save(reminder);
    }

    /**
     * Returns a reminder after validating ownership.
     */
    @Override
    public Reminder getById(String userId, String reminderId) {

        return getOwnedReminder(userId, reminderId);
    }

    /**
     * Returns all reminders belonging to a user.
     */
    @Override
    public List<Reminder> getAllByUser(String userId) {

        validateUser(userId);

        return reminderRepository.findAllByUserId(new UserId(userId));
    }

    /**
     * Returns only completed reminders for a user.
     */
    @Override
    public List<Reminder> getCompletedReminders(String userId) {

        validateUser(userId);

        return reminderRepository.findAllByUserId(new UserId(userId))
                .stream()
                .filter(Reminder::isCompleted)
                .toList();
    }

    /**
     * Returns only pending reminders for a user.
     */
    @Override
    public List<Reminder> getPendingReminders(String userId) {

        validateUser(userId);

        return reminderRepository.findAllByUserId(new UserId(userId))
                .stream()
                .filter(reminder -> !reminder.isCompleted())
                .toList();
    }

    /**
     * Returns pending reminders using pagination.
     *
     * Used by the scheduler to process reminders in batches.
     */
    @Override
    public Page<Reminder> getPendingReminders(PageRequest request) {
        return reminderRepository.findAllByIsCompleted(request, false);
    }

    /**
     * Loads a reminder and validates ownership.
     *
     * @throws IllegalArgumentException if reminder is not found
     *                                  or user does not own the reminder
     */
    private Reminder getOwnedReminder(String userId, String reminderId) {

        validateUser(userId);

        Reminder reminder = reminderRepository
                .findById(new ReminderId(reminderId))
                .orElseThrow(() ->
                        new IllegalArgumentException("Reminder not found")
                );

        if (!reminder.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized reminder access");
        }

        return reminder;
    }

    /**
     * Loads a reminder without ownership validation.
     *
     * Intended for internal system operations
     * such as scheduler processing.
     */
    private Reminder getOwnedReminder(String reminderId) {

        return reminderRepository
                .findById(new ReminderId(reminderId))
                .orElseThrow(() ->
                        new IllegalArgumentException("Reminder not found")
                );
    }

    /**
     * Validates that a user exists.
     *
     * @throws UserNotFoundException when user does not exist
     */
    private void validateUser(String userId) {

        userRepository.findById(new UserId(userId))
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Validates that a note belongs to the user.
     *
     * @throws IllegalArgumentException when user does not own the note
     */
    private void validateNoteOwnership(String userId, Note note) {

        if (!note.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized note access");
        }
    }
}
