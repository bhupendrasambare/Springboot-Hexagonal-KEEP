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

    private static final int PAGE_SIZE = 10;

    private final ReminderUseCase reminderUseCase;
    private final ReminderProcessor reminderProcessor;

    @Scheduled(fixedDelay = 30000)
    public void checkReminders() {

        long start = System.currentTimeMillis();

        log.info("Reminder scheduler started");

        int page = 0;
        int totalProcessed = 0;

        Page<Reminder> reminders;

        try {

            do {

                log.debug(
                        "Fetching pending reminders. page={}, size={}",
                        page,
                        PAGE_SIZE
                );

                reminders = reminderUseCase.getPendingReminders(
                        PageRequest.of(page, PAGE_SIZE)
                );

                log.info(
                        "Fetched page {} of {}. remindersFound={}",
                        page + 1,
                        reminders.getTotalPages(),
                        reminders.getNumberOfElements()
                );

                reminders.forEach(reminder -> {

                    log.debug(
                            "Submitting reminder for async processing. reminderId={}",
                            reminder.getId().getValue()
                    );

                    reminderProcessor.processReminder(reminder);
                });

                totalProcessed += reminders.getNumberOfElements();

                page++;

            } while (page < reminders.getTotalPages());

            log.info(
                    "Reminder scheduler completed. totalProcessed={}, duration={}ms",
                    totalProcessed,
                    System.currentTimeMillis() - start
            );

        } catch (Exception e) {

            log.error(
                    "Reminder scheduler failed after processing {} reminders",
                    totalProcessed,
                    e
            );
        }
    }
}