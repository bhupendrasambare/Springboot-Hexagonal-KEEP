package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Note;

import java.util.List;

public interface NoteUseCase {

    Note creatNote(String userId, String title, String description, List<String> tagsIds);

    Note updateNote(String userId, String noteId, String title, String description, List<String> tagIds);

    void deleteNote(String userId, String noteId);

    void pin(String userId, String noteId);

    void unPin(String userId, String noteId);

    void archive(String userId, String noteId);

    void moveToTrash(String userId, String noteId);

    List<Note> getAllNotes(String userId);

}
