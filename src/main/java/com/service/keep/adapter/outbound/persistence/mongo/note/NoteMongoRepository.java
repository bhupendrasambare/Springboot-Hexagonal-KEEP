/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NoteMongoRepository extends MongoRepository<NoteDocument, String> {

    Page<NoteDocument> findByUserIdAndPinnedAndArchivedAndTrashedAndTitleContainingIgnoreCase(
            String userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String title,
            Pageable pageable
    );

    List<NoteDocument> findByUserId(String userId);

    Page<NoteDocument> findByMetaDataFlag(Boolean metaDataFlag, Pageable pageable);

    @Query("""
    {
      "userId": ?0,
      "$or": [
        { "keywords": { "$in": ?1 } },
        { "tags": { "$in": ?2 } },
        { "title": { "$regex": ?3, "$options": "i" } }
      ]
    }
    """)
    List<NoteDocument> searchByAi(
            String userId,
            List<String> keywords,
            List<String> tags,
            String title
    );
}
