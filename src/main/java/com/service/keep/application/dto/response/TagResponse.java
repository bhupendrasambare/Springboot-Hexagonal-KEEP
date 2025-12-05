/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:57 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Builder
public class TagResponse {
    private String id;
    private String userId;
    private String name;
    private String color;
    private String imageUri;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
