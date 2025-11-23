package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.AuthToken;

import java.util.Optional;

public interface AuthTokenRepositoryPort {

    AuthToken save(AuthToken authToken);

    Optional<AuthToken> findByToken(String token);

    boolean deleteBuToken(String token);
}
