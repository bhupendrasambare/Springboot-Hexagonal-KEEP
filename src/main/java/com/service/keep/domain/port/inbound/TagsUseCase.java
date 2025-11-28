package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Tags;

import java.util.List;

public interface TagsUseCase {

    Tags create(String userId, String name, String color, String imageUri);

    Tags rename(String userId, String tagId, String newName);

    void delete(String userId, String tagId);

    List<Tags> getAllTags(String userId);

}
