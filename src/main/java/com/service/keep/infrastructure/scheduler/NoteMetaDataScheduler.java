/**
 * author @bhupendrasambare
 * Date   :10/04/26
 * Time   :10:59 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NoteMetaDataScheduler {

    // TODO: change generateMetadata function to async and call it from scheduler
    @Scheduled(fixedDelay = 1000)
    public void updateMetaData(){}

}
