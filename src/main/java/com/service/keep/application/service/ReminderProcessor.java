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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReminderProcessor {

    private final ReminderUseCase reminderUseCase;
    private final NotificationUseCase notificationUseCase;
    private final UserProfileUseCase userProfileUseCase;

    @Async("virtualThreadExecutor")
    @Transactional
    public void processReminder(Reminder reminder) {

        String reminderId = reminder.getId().getValue();

        log.info(
                "Processing reminder. reminderId={}, userId={}, reminderTime={}",
                reminderId,
                reminder.getUserId().getValue(),
                reminder.getReminderTime()
        );

        long start = System.currentTimeMillis();

        try {

            User user = userProfileUseCase.getUserProfile(
                    reminder.getUserId().getValue()
            );

            log.debug(
                    "User profile loaded. reminderId={}, email={}",
                    reminderId,
                    user.getEmail().getValue()
            );

            notificationUseCase.sendEmail(
                    user.getEmail().getValue(),
                    reminder.getTitle(),
                    reminder.getDescription()
            );

            log.info(
                    "Email notification sent successfully. reminderId={}, email={}",
                    reminderId,
                    user.getEmail().getValue()
            );

            reminderUseCase.markCompletedBySystem(reminderId);

            log.info(
                    "Reminder marked completed. reminderId={}",
                    reminderId
            );

            log.info(
                    "Reminder processed successfully. reminderId={}, duration={}ms",
                    reminderId,
                    System.currentTimeMillis() - start
            );

        } catch (Exception e) {

            log.error(
                    "Failed to process reminder. reminderId={}, duration={}ms",
                    reminderId,
                    System.currentTimeMillis() - start,
                    e
            );
        }
    }
}
