/**
 * author @bhupendrasambare
 * Date   :10/04/26
 * Time   :10:59 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.scheduler;

import com.service.keep.application.dto.response.MetadataResponse;
import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.AiSearchPort;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoteMetaDataScheduler {

    private final AiSearchPort aiSearchPort;
    private final NoteUseCase noteUseCase;


    @Scheduled(fixedDelay = 1000)
    public void updateMetaData(){
        boolean isMore = true;
        while(isMore){
            isMore = false;
            for (Note note: noteUseCase.getAllNonMetaDataNotes(10)){
                isMore = true;
                MetadataResponse response = aiSearchPort.generateMetadata(note.getTitle(), note.getDescription());

                note.updateMetadata(response.getTags(), response.getKeywords(), response.getSummary());
                note.setMetaDataFlag(true);

                noteUseCase.saveNoteInternal(note);

            }
        }

    }

}
