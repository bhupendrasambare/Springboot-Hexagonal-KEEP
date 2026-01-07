/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);

    Optional<UserDocument> findByUsername(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String email);
}
