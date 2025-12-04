/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:25 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class NoteResponse {

    private String id;
    private String userId;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;
    private String reminder;
    private final List<String> tagId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
