/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:25 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NoteCreateRequest {

    private String title;

    private String description;

    private String reminder;

    private List<String> tagIds;

}
