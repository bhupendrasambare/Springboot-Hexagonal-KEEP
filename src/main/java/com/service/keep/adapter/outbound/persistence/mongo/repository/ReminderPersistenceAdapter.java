/**
 * author @bhupendrasambare
 * Date   :16/05/26
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReminderPersistenceAdapter {

    private final ReminderMongoRepository repository;
}
