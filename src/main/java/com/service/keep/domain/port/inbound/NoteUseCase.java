package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Note;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteUseCase {

    Note create(String userId, String title, String description, String reminder, String tagId);

    Note update(String userId, String noteId, String title, String description, String tagId);

    void delete(String userId, String noteId);

    void pin(String userId, String noteId);

    void unPin(String userId, String noteId);

    void archive(String userId, String noteId);

    void unArchive(String userId, String noteId);

    void moveToTrash(String userId, String noteId);

    void restore(String userId, String noteId);

    List<Note> getAll(String userId, boolean pin, boolean archive, boolean trashed, String keyword, Integer page, Integer pageSize);
}

