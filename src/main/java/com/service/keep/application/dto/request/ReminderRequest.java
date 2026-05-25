/**
 * author @bhupendrasambare
 * Date   :25/05/26
 * Time   :11:08 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReminderRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String noteId;

    private LocalDateTime reminderTime;

    private String description;

    private Boolean completed;

}
