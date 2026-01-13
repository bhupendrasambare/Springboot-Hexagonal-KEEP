/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:22 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteApplicationService implements NoteUseCase {

    private final NoteRepositoryPort noteRepository;

    @Override
    public Note create(String userId, String title, String description, String reminder, String tagId) {

        Note note = new Note(
                new NoteId(UUID.randomUUID().toString()),
                new UserId(userId),
                title,
                description,
                false,
                false,
                false,
                reminder,
                tagId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return noteRepository.save(note);
    }

    @Override
    public Note update(String userId, String noteId, String title, String description, String tagId) {

        Note note = getOwnedNote(userId, noteId);
        note.update(title, description, tagId);
        return noteRepository.save(note);
    }

    @Override
    public void delete(String userId, String noteId) {
        getOwnedNote(userId, noteId);
        noteRepository.deleteById(new NoteId(noteId));
    }

    @Override
    public void pin(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.pin();
        noteRepository.save(note);
    }

    @Override
    public void unPin(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.unpin();
        noteRepository.save(note);
    }

    @Override
    public void archive(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.archive();
        noteRepository.save(note);
    }

    @Override
    public void unArchive(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.unarchive();
        noteRepository.save(note);
    }

    @Override
    public void moveToTrash(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.moveToTrash();
        noteRepository.save(note);
    }

    @Override
    public void restore(String userId, String noteId) {
        Note note = getOwnedNote(userId, noteId);
        note.restoreFromTrash();
        noteRepository.save(note);
    }

    @Override
    public List<Note> getAll(
            String userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String keyword,
            Integer page,
            Integer pageSize
    ) {
        return noteRepository.findByUserId(
                new UserId(userId),
                pinned,
                archived,
                trashed,
                keyword,
                page == null ? 0 : page,
                pageSize == null ? 10 : pageSize
        );
    }

    private Note getOwnedNote(String userId, String noteId) {
        Note note = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!note.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized request");
        }
        return note;
    }
}
