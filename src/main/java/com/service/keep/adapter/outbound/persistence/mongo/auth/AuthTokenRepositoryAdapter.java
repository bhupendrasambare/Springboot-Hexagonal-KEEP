/**
 * author @bhupendrasambare
 * Date   :15/01/26
 * Time   :11:03 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.auth;

import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.port.outbound.AuthTokenRepositoryPort;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthTokenRepositoryAdapter implements AuthTokenRepositoryPort {

    private final AuthTokenMongoRepository repository;

    @Override
    public AuthToken save(AuthToken token) {
        AuthTokenDocument saved = repository.save(toDocument(token));
        return toDomain(saved);
    }

    @Override
    public Optional<AuthToken> findByToken(String token) {
        return repository.findById(token)
                .map(this::toDomain);
    }

    @Override
    public void deleteByToken(String token) {
        repository.deleteById(token);
    }

    /* ---------------- MAPPERS ---------------- */

    private AuthTokenDocument toDocument(AuthToken token) {
        return AuthTokenDocument.builder()
                .token(token.getToken())
                .userId(token.getUserId().getValue())
                .username(token.getUsername())
                .expired(token.isExpired())
                .createdAt(token.getCreatedAt())
                .expiresAt(token.getExpiresAt())
                .build();
    }

    private AuthToken toDomain(AuthTokenDocument doc) {
        AuthToken authToken = new AuthToken(
                doc.getToken(),
                new UserId(doc.getUserId()),
                doc.getUsername(),
                doc.getCreatedAt(),
                doc.getExpiresAt()
        );

        if (doc.isExpired()) {
            authToken.markExpired();
        }

        return authToken;
    }
}
