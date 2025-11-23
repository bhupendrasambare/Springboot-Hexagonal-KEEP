package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.User;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.UserId;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(Email email);

    Optional<User> findById(UserId id);

    User save(User user);

}
