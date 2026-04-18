/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:22 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.response.SearchQueryResponse;
import com.service.keep.application.exception.NoteNotFoundException;
import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.AiSearchPort;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteApplicationService implements NoteUseCase {

    private final NoteRepositoryPort noteRepository;
    private final AiSearchPort aiSearchPort;

    @Override
    public Note create(String userId, String title, String description,
                       String reminder, String tagId) {

        var metadata = aiSearchPort.generateMetadata(title, description);

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
        note.setMetaDataFlag(false);

        return noteRepository.save(note);
    }

    @Override
    public Note update(String userId, String noteId, String title, String description, String tagId) {

        Note note = getOwnedNote(userId, noteId);
        note.update(title, description, tagId);
        note.setMetaDataFlag(false);
        return noteRepository.save(note);
    }

    public List<Note> aiSearch(String userId, String prompt) {

        var searchMeta = aiSearchPort.parseSearchQuery(prompt);

        List<Note> notes = noteRepository.findAllByUserId(new UserId(userId));

        return notes.stream()
                .filter(note ->
                        containsAny(note.getKeywords(), searchMeta.getKeywords()) ||
                                containsAny(note.getTags(), searchMeta.getTags()) ||
                                note.getTitle().toLowerCase().contains(prompt.toLowerCase())
                )
                .toList();
    }

    private boolean containsAny(List<String> source, List<String> target) {
        if (source == null || target == null) return false;

        return source.stream()
                .anyMatch(s ->
                        target.stream().anyMatch(t ->
                                s.toLowerCase().contains(t.toLowerCase())
                        )
                );
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
    public Note saveNoteInternal(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Page<Note> getAllNonMetaDataNotes(Integer size) {
        Pageable pageable = PageRequest.of(0,size);
        return noteRepository.findAllByMetaDataFlag(Boolean.FALSE, pageable);
    }

    @Override
    public Page<Note> getAll(
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

    @Override
    public Page<Note> findByAi(String request) {
        SearchQueryResponse response = aiSearchPort.parseSearchQuery(request);
        noteRepository.findAllByMetaDataFlag()
        response.getTags();
    }

    private Note getOwnedNote(String userId, String noteId) {
        Note note = noteRepository.findById(new NoteId(noteId))
                .orElseThrow(NoteNotFoundException::new);

        if (!note.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized request");
        }
        return note;
    }
}
