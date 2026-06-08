/**
 * author @bhupendrasambare
 * Date   :08/06/26
 * Time   :10:26 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.NotificationUseCase;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderProcessor {

    private final ReminderUseCase reminderUseCase;
    private final NotificationUseCase notificationUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @Async("virtualThreadExecutor")
    public void processReminder(Reminder reminder) {

        try {

            User user = userProfileUseCase.getUserProfile(
                    reminder.getUserId().getValue()
            );

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
