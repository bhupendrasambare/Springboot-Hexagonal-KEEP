/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:46 am
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.AuthToken;

import java.util.Optional;

public interface AuthTokenRepositoryPort {

    AuthToken save(AuthToken token);

    Optional<AuthToken> findByToken(String token);

    void deleteByToken(String token);
}
