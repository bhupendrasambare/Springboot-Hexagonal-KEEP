package com.service.keep.domain.user.repository;

import com.service.keep.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryPort extends MongoRepository<User, String> {
}
