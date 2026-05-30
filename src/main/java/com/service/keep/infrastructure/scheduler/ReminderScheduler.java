/**
 * author @bhupendrasambare
 * Date   :30/05/26
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReminderScheduler {

    @Scheduled(fixedDelay = 1000)
    public void checkReminders(){

    }
}
