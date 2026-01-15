/**
 * author @bhupendrasambare
 * Date   :15/01/26
 * Time   :11:02 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthTokenMongoRepository
        extends MongoRepository<AuthTokenDocument, String> {
}
