/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.tags;

import com.service.keep.domain.model.Tags;
import com.service.keep.domain.port.outbound.TagRepositoryPort;
import com.service.keep.domain.valueobject.TagsId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TagPersistenceAdapter implements TagRepositoryPort {

    private final TagMongoRepository repository;


    @Override
    public Tags save(Tags tags) {
        TagDocument saved = this.repository.save(toDocument(tags));
        return this.toDomain(saved);
    }

    @Override
    public Optional<Tags> findById(TagsId id) {
        return this.repository.findById(id.getValue())
                .map(this::toDomain);
    }

    @Override
    public List<Tags> findAllUserId(UserId userId) {
        return this.repository
                .findAllByUserId(userId.getValue())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(TagsId id) {
        this.repository.deleteById(id.getValue());
    }



    private TagDocument toDocument(Tags t) {
        TagDocument d = new TagDocument();
        d.setId(t.getId().getValue());
        d.setUserId(t.getUserId().getValue());
        d.setColor(t.getColor());
        d.setImageUri(t.getImageUri());
        d.setUserName(t.getUserName());
        d.setCreatedAt(t.getCreatedAt());
        d.setUpdatedAt(t.getUpdatedAt());
        return d;
    }

    private Tags toDomain(TagDocument d) {
        return new Tags(
                new TagsId(d.getId()),
                new UserId(d.getUserId()),
                d.getColor(),
                d.getImageUri(),
                d.getUserName(),
                d.getCreatedAt(),
                d.getUpdatedAt()
        );
    }
}
