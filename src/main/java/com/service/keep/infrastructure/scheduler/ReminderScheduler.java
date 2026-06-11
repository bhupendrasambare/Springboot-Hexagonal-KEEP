/**
 * author @bhupendrasambare
 * Date   :30/05/26
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import com.service.keep.application.service.ReminderProcessor;
import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private final ReminderUseCase reminderUseCase;
    private final ReminderProcessor reminderProcessor;

    @Scheduled(fixedDelay = 30000)
    public void checkReminders() {

        int page = 0;
        Page<Reminder> reminders;

        do {

            reminders = reminderUseCase.getPendingReminders(
                    PageRequest.of(page, 10)
            );

            reminders.forEach(reminder ->
                    reminderProcessor.processReminder(reminder)
            );

            page++;

        } while (page < reminders.getTotalPages());
    }
}