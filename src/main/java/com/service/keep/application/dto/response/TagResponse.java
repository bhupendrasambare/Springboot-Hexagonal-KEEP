/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:57 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private String id;
    private String userId;
    private String name;
    private String color;
    private String imageUri;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
