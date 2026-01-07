/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.note;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteMongoRepository extends MongoRepository<NoteDocument, String> {

    List<NoteDocument> findAllByUserId(String userId);
}
