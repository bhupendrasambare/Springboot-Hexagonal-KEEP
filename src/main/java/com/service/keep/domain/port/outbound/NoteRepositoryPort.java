package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoteRepositoryPort {

    Note save(Note note);

    Page<Note> findByUserId(
            UserId userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String keyword,
            int page,
            int pageSize
    );


    Optional<Note> findById(NoteId id);

    List<Note> findAllByUserId(UserId userId);

    Page<Note> findAllByMetaDataFlag(Boolean metaDataFlag, Pageable pageable);
}
