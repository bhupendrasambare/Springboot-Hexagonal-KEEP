/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistance.mongo.tags;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document(collation = "tags")
public class TagDocument {


    @Id
    private String id;

    private String userId;

    private String color;
    private String imageUri;
    private String userName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
