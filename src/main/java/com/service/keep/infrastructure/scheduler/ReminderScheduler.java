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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReminderScheduler {

    private ReminderUseCase reminderUseCase;

    @Scheduled(fixedDelay = 1000)
    public void checkReminders(){

        List<Reminder> reminders = reminderUseCase.getPendingReminders();


    }
}
