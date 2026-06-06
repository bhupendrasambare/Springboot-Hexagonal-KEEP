/**
 * author @bhupendrasambare
 * Date   :16/05/26
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.repository;

import com.service.keep.domain.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReminderMongoRepository extends MongoRepository<ReminderDocument, String> {


    List<ReminderDocument> findAllByUserId(String value);

    Page<ReminderDocument> findAllByUserId(String value, Pageable request);

    Page<Reminder> findAllByCompleted(PageRequest request, Boolean completed);
}
