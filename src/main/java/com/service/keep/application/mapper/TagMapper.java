/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:58 pm
 * Project:Keep
 **/
package com.service.keep.application.mapper;

import com.service.keep.application.dto.response.TagResponse;
import com.service.keep.domain.model.Tags;

public final class TagMapper {
    private TagMapper() {}

    public static TagResponse toResponse(Tags t) {
        return TagResponse.builder()
                .id(t.getId().getValue())
                .userId(t.getUserId().getValue())
                .name(t.getUserName())
                .color(t.getColor())
                .imageUri(t.getImageUri())
                .createdAt(t.getCreatedAt())
                .updatedAt(t.getUpdatedAt())
                .build();
    }
}
