/**
 * author @bhupendrasambare
 * Date   :17/11/25
 * Time   :10:55 pm
 * Project:Keep
 **/
package com.service.keep.domain.repository;

import com.service.keep.domain.model.ResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepositoryPort extends MongoRepository<ResetToken, String> {
}
