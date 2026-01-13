/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:25 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoteCreateRequest {

    @NotBlank
    private String title;

    private String description;

    private String reminder;

    private String tagId;

}
