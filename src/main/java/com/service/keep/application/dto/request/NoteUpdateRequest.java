/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:37 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoteUpdateRequest {

    private String noteId;

    private String title;

    private String description;

    private String reminder;

    private String tagId;

}
