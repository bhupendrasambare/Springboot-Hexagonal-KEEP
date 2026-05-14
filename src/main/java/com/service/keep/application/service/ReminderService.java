/**
 * author @bhupendrasambare
 * Date   :14/05/26
 * Time   :11:42 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService implements ReminderUseCase {




    @Override
    public Reminder create(String userId, String noteId, String title, String description) {
        return null;
    }

    @Override
    public Reminder update(String userId, String reminderId, String title, String description, Boolean completed) {
        return null;
    }

    @Override
    public void delete(String userId, String reminderId) {

    }

    @Override
    public void markCompleted(String userId, String reminderId) {

    }

    @Override
    public void markIncomplete(String userId, String reminderId) {

    }

    @Override
    public Reminder getById(String userId, String reminderId) {
        return null;
    }

    @Override
    public List<Reminder> getAllByUser(String userId) {
        return null;
    }

    @Override
    public List<Reminder> getCompletedReminders(String userId) {
        return null;
    }

    @Override
    public List<Reminder> getPendingReminders(String userId) {
        return null;
    }
}
