/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:56 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.exception.TagNotFoundException;
import com.service.keep.application.exception.UnauthorizedTagAccessException;
import com.service.keep.domain.model.Tags;
import com.service.keep.domain.port.inbound.TagsUseCase;
import com.service.keep.domain.port.outbound.TagRepositoryPort;
import com.service.keep.domain.valueobject.TagsId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagsService implements TagsUseCase {

    private final TagRepositoryPort tagsRepository;

    @Override
    public Tags create(String userId, String name, String color, String imageUri) {
        Tags tags = new Tags(
                new TagsId(UUID.randomUUID().toString()),
                new UserId(userId),
                color,
                imageUri,
                name,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return tagsRepository.save(tags);
    }

    public Tags rename(String userId, String tagId, String newName) {
        Tags t = tagsRepository.findById(new TagsId(tagId))
                .orElseThrow(TagNotFoundException::new);
        if (!t.getUserId().getValue().equals(userId)) throw new UnauthorizedTagAccessException();
        t.rename(newName);
        return tagsRepository.save(t);
    }

    public void delete(String userId, String tagId) {
        Tags t = tagsRepository.findById(new TagsId(tagId))
                .orElseThrow(TagNotFoundException::new);
        if (!t.getUserId().getValue().equals(userId)) throw new UnauthorizedTagAccessException();
        tagsRepository.deleteById(new TagsId(tagId));
    }

    @Override
    public List<Tags> getAllTags(String userId) {
        return tagsRepository.findAllUserId(new UserId(userId));
    }
}
