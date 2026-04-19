/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.note;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotePersistenceAdapter implements NoteRepositoryPort {

    private final NoteMongoRepository repository;

    @Override
    public Note save(Note note) {
        NoteDocument saved = repository.save(toDocument(note));
        return toDomain(saved);
    }

    @Override
    public Optional<Note> findById(NoteId id) {
        return repository.findById(id.getValue()).map(this::toDomain);
    }

    @Override
    public List<Note> findAllByUserId(UserId userId) {
        return repository.findByUserId(userId.getValue()).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Page<Note> findAllByMetaDataFlag(Boolean metaDataFlag, Pageable pageable) {

        Page<NoteDocument> result = repository
                .findByMetaDataFlag(metaDataFlag,pageable);

        return result.map(this::toDomain);
    }

    @Override
    public Page<Note> findByUserId(
            UserId userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String keyword,
            int page,
            int pageSize
    ) {
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());

        Page<NoteDocument> result = repository
                .findByUserIdAndPinnedAndArchivedAndTrashedAndTitleContainingIgnoreCase(
                        userId.getValue(),
                        pinned,
                        archived,
                        trashed,
                        keyword == null ? "" : keyword,
                        pageable
                );

        return result.map(this::toDomain);
    }

    @Override
    public List<Note> searchByAi(UserId userId, List<String> keywords, List<String> tags, String title) {

        return repository
                .searchByAi(userId.getValue(), keywords, tags, title)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // ---------------- MAPPERS ----------------

    private NoteDocument toDocument(Note note) {
        NoteDocument d = new NoteDocument();
        d.setId(note.getId().getValue());
        d.setUserId(note.getUserId().getValue());
        d.setTitle(note.getTitle());
        d.setDescription(note.getDescription());
        d.setPinned(note.isPinned());
        d.setArchived(note.isArchived());
        d.setTrashed(note.isTrashed());
        d.setReminder(note.getReminder());
        d.setTagId(note.getTagId());
        d.setTags(note.getTags());
        d.setKeywords(note.getKeywords());
        d.setSummary(note.getSummary());
        d.setCreatedAt(note.getCreatedAt());
        d.setUpdatedAt(note.getUpdatedAt());
        return d;
    }

    private Note toDomain(NoteDocument d) {
        return new Note(
                new NoteId(d.getId()),
                new UserId(d.getUserId()),
                d.getTitle(),
                d.getDescription(),
                d.getPinned(),
                d.getArchived(),
                d.getTrashed(),
                d.getReminder(),
                d.getTagId(),
                d.getCreatedAt(),
                d.getUpdatedAt()
        );
    }
}
