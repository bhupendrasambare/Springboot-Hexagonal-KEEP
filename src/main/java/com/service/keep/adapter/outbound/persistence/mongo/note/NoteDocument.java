/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.note;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document("notes")
public class NoteDocument {

    @Id
    private String id;

    private String userId;

    private String title;
    private String description;

    private Boolean pinned;
    private Boolean archived;
    private Boolean trash;

    private String reminder;

    private List<String> tagId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
