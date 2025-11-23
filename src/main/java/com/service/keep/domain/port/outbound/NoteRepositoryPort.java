package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;

import java.util.List;
import java.util.Optional;

public interface NoteRepositoryPort {

    Note save(Note note);

    List<Note> findByUserId(UserId userId);

    Optional<Note> findById(NoteId id);

    boolean delete(NoteId noteId);
}
