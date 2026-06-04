/**
 * author @bhupendrasambare
 * Date   :30/05/26
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.NotificationUseCase;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private final ReminderUseCase reminderUseCase;

    private final NotificationUseCase notificationUseCase;

    private final UserProfileUseCase userProfileUseCase;

    private final ExecutorService reminderExecutor;

    @Scheduled(fixedDelay = 30000)
    public void checkReminders() {

        int page = 0;

        Page<Reminder> reminders;

        do {

            reminders =
                    reminderUseCase.getPendingReminders(
                            PageRequest.of(page, 100)
                    );

            reminders.forEach(reminder ->
                    reminderExecutor.submit(
                            () -> processReminder(reminder)
                    )
            );

            page++;

        } while (page < reminders.getTotalPages());
    }

    private void processReminder(
            Reminder reminder
    ) {

        try {
            User user = userProfileUseCase.getUserProfile(reminder.getUserId().getValue());
            notificationUseCase.sendEmail(
                    user.getEmail().getValue(),
                    reminder.getTitle(),
                    reminder.getDescription()
            );

            reminderUseCase.markCompleted(
                    null,
                    reminder.getId().getValue()
            );

        } catch (Exception e) {

            log.error(
                    "Failed reminder {}",
                    reminder.getId().getValue(),
                    e
            );
        }
    }
}