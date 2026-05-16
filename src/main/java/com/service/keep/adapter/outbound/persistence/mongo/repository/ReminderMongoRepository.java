/**
 * author @bhupendrasambare
 * Date   :16/05/26
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReminderMongoRepository extends MongoRepository<ReminderDocument, String> {


}
