/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:22 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.request.NoteCreateRequest;
import com.service.keep.application.dto.response.NoteResponse;
import com.service.keep.application.mapper.NoteMapper;
import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private NoteRepositoryPort noteRepository;

    public NoteResponse createNote(String userId, NoteCreateRequest request){
        Note note = new Note(
                new NoteId(UUID.randomUUID().toString()),
                new UserId(userId),
                request.getTitle(),
                request.getDescription(),
                false,
                false,
                false,
                request.getReminder(),
                request.getTagIds(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Note savedNote = noteRepository.save(note);
        return NoteMapper.toNoteResponse(savedNote);

    }


}
