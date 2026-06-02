/**
 * author @bhupendrasambare
 * Date   :30/05/26
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReminderScheduler {

    private ReminderUseCase reminderUseCase;

    @Scheduled(fixedDelay = 1000)
    public void checkReminders(){
        int page = 0;
        int size = 10;
        int total = 0;
        PageRequest request = PageRequest.of(page, size);
        do{
            Page<Reminder> reminders = reminderUseCase.getPendingReminders(request);
            total = reminders.getTotalPages();

            for(Reminder reminder: reminders){
                // TODO make the notification and emails based on the configuration

                reminderUseCase.markCompleted(null, reminder.getId().getValue());
            }
        }while(total > page);
    }
}
