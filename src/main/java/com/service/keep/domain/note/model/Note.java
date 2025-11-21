/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:50 pm
 * Project:Keep
 **/
package com.service.keep.domain.note.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    private String id;

    private String title;

    private String description;

    private String color;

    private String tagId;

    private Boolean pinned;

    private Boolean archived;

    private Boolean trash;

    private String reminder;
}
