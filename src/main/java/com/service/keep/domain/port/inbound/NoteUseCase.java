package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Note;

import java.util.List;

public interface NoteUseCase {

    Note create(String userId, String title, String description, String reminder, List<String> tagIds);

    Note update(String userId, String noteId, String title, String description, List<String> tagIds);

    void delete(String userId, String noteId);

    void pin(String userId, String noteId);

    void unPin(String userId, String noteId);

    void archive(String userId, String noteId);

    void unArchive(String userId, String noteId);

    void moveToTrash(String userId, String noteId);

    void restore(String userId, String noteId);

    List<Note> getAll(String userId);
}

