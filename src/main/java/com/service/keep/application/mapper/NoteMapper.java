/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:29 pm
 * Project:Keep
 **/
package com.service.keep.application.mapper;

import com.service.keep.application.dto.response.NoteResponse;
import com.service.keep.domain.model.Note;

public class NoteMapper {

    public static NoteResponse toNoteResponse(Note note){
        return NoteResponse.builder()
                .id(note.getId().getValue())
                .userId(note.getUserId().getValue())
                .title(note.getTitle())
                .description(note.getDescription())
                .pinned(note.isPinned())
                .archived(note.isArchived())
                .trashed(note.isTrashed())
                .reminder(note.getReminder())
                .tagId(note.getTagId())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

//    public static Note toDomain()

}
