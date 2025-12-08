/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistance.mongo.user;

import com.service.keep.application.mapper.UserMapper;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserMongoRepository userMongoRepository;

    @Override
    public Optional<User> findByEmail(Email email) {
        return userMongoRepository.findByEmail(email.getValue())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userMongoRepository.findByUsername(userName)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return userMongoRepository.findById(id.getValue())
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userMongoRepository.existsByEmail(email.getValue());
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userMongoRepository.existsByUsername(userName);
    }

    @Override
    public User save(User user) {
        UserDocument document = toDocument(user);
        UserDocument savedUser = userMongoRepository.save(document);

        return toDomain(savedUser);
    }

    private User toDomain(UserDocument document){
        return new User(
                new UserId(document.getId()),
                document.getUsername(),
                document.getFirstName(),
                document.getLastName(),
                new Email(document.getEmail()),
                new HashedPassword(document.getPasswordHash()),
                document.getCreatedAt(),
                document.getUpdatedAt()
        );

    }

    private UserDocument toDocument(User user){
        UserDocument document = new UserDocument();
        document.setId(user.getId().getValue());
        document.setUsername(user.getUsername());
        document.setFirstName(user.getFirstName());
        document.setLastName(user.getLastName());
        document.setEmail(user.getEmail().getValue());
        document.setPasswordHash(user.getPasswordHash().getValue());

        document.setCreatedAt(user.getCreatedAt());
        document.setUpdatedAt(user.getUpdatedAt());

        return document;
    }

}
