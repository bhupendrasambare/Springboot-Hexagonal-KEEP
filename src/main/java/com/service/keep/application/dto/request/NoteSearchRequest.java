/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:25 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteSearchRequest {

    private Boolean pinned;

    private Boolean archived;

    private Boolean trashed;

    private String keyword;

    private Integer page = 0;

    private Integer pageSize = 10;

}
