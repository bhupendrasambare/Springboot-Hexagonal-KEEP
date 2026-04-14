/**
 * author @bhupendrasambare
 * Date   :10/04/26
 * Time   :10:59 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.outbound.AiSearchPort;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoteMetaDataScheduler {

    private final AiSearchPort aiSearchPort;

    @Scheduled(fixedDelay = 1000)
    public void updateMetaData(){


    }

}
