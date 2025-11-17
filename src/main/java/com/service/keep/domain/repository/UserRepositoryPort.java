package com.service.keep.domain.repository;

import com.service.keep.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryPort extends MongoRepository<User, String> {
}
