/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistance.mongo.tags;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagMongoRepository extends MongoRepository<TagDocument, String> {

    List<TagDocument> findAllByUserId(String userId);
}
