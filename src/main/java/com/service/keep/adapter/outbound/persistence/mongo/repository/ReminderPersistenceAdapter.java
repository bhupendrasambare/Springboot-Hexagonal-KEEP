/**
 * author @bhupendrasambare
 * Date   :16/05/26
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.repository;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.outbound.ReminderRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.ReminderId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReminderPersistenceAdapter implements ReminderRepositoryPort {

    private final ReminderMongoRepository repository;

    @Override
    public Reminder save(Reminder reminder) {

        ReminderDocument saved = repository.save(toDocument(reminder));

        return toDomain(saved);
    }

    @Override
    public Page<Reminder> findByUserId(
            UserId userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String keyword,
            int page,
            int pageSize
    ) {

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<ReminderDocument> reminderPage =
                repository.findAllByUserId(userId.getValue(), pageable);

        List<Reminder> reminders = reminderPage.getContent()
                .stream()
                .map(this::toDomain)
                .toList();

        return new PageImpl<>(
                reminders,
                pageable,
                reminderPage.getTotalElements()
        );
    }

    @Override
    public Optional<Reminder> findById(ReminderId id) {

        return repository.findById(id.getValue())
                .map(this::toDomain);
    }

    @Override
    public List<Reminder> findAllByUserId(UserId userId) {

        return repository.findAllByUserId(userId.getValue())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Reminder> findAllByIsCompleted(
            PageRequest request,
            Boolean completed
    ) {

        return repository
                .findAllByCompleted(completed, request)
                .map(this::toDomain);
    }

    @Override
    public Page<Reminder> findAllByMetaDataFlag(Boolean metaDataFlag, Pageable pageable) {

        Page<ReminderDocument> reminderPage =
                repository.findAll(pageable);

        List<Reminder> reminders = reminderPage.getContent()
                .stream()
                .map(this::toDomain)
                .toList();

        return new PageImpl<>(
                reminders,
                pageable,
                reminderPage.getTotalElements()
        );
    }

    @Override
    public List<Reminder> searchByAi(
            UserId userId,
            List<String> keywords,
            List<String> tags,
            String title
    ) {

        return repository
                .findAllByUserId(userId.getValue())
                .stream()
                .map(this::toDomain)
                .filter(reminder ->
                        reminder.getTitle().toLowerCase()
                                .contains(title == null ? "" : title.toLowerCase())
                )
                .toList();
    }

    private ReminderDocument toDocument(Reminder reminder) {

        ReminderDocument document = new ReminderDocument();

        document.setId(reminder.getId().getValue());
        document.setNoteId(reminder.getNoteId().getValue());
        document.setUserId(reminder.getUserId().getValue());

        document.setTitle(reminder.getTitle());
        document.setDescription(reminder.getDescription());

        document.setCompleted(reminder.isCompleted());

        document.setCreatedAt(reminder.getCreatedAt());
        document.setUpdatedAt(reminder.getUpdatedAt());

        return document;
    }

    private Reminder toDomain(ReminderDocument document) {

        return new Reminder(
                new ReminderId(document.getId()),
                new NoteId(document.getNoteId()),
                new UserId(document.getUserId()),
                document.getReminderTime(),
                document.getTitle(),
                document.getDescription(),
                document.isCompleted(),
                document.getCreatedAt(),
                document.getUpdatedAt()
        );
    }
}
