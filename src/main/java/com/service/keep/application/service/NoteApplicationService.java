/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:22 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.request.NoteCreateRequest;
import com.service.keep.application.dto.request.NoteUpdateRequest;
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
public class NoteApplicationService {

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

    public NoteResponse update(String  userId, NoteUpdateRequest request){
        Note existingNote = noteRepository.findById(new NoteId(request.getNoteId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Note id"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.update(request.getTitle(), request.getDescription(), request.getTagId());
        Note saved = noteRepository.save(existingNote);

        return NoteMapper.toNoteResponse(saved);
    }

    public void delete(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        noteRepository.deleteById(new NoteId(noteId));
    }

    public void pin(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.pin();
        noteRepository.save(existingNote);
    }

    public void unPin(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.unpin();
        noteRepository.save(existingNote);
    }

    public void archive(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.archive();
        noteRepository.save(existingNote);
    }

    public void unArchive(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.unarchive();
        noteRepository.save(existingNote);
    }

    public void trash(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.moveToTrash();
        noteRepository.save(existingNote);
    }

    public void restore(String userId, String noteId){
        Note existingNote = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if(!existingNote.getUserId().getValue().equals(userId)){
            throw new IllegalArgumentException("Unauthorized request");
        }

        existingNote.restoreFromTrash();
        noteRepository.save(existingNote);
    }


}
