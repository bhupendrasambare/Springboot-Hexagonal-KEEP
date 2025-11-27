/**
 * author @bhupendrasambare
 * Date   :24/11/25
 * Time   :11:02 pm
 * Project:Keep
 **/
package com.service.keep.application.auth;

import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.outbound.AuthTokenRepositoryPort;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResetPasswordService {

    private final AuthTokenRepositoryPort authTokenRepository;
    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;

    public boolean resetPassword(String token, String newPassword){
        AuthToken authToken = authTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (authToken.getIsExpired()){
            throw new IllegalArgumentException("Token expired");
        }

        User user = userRepository.findById(new UserId(authToken.getUserId().getUserid()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPasswordHash(new HashedPassword(passwordHarsher.hash(newPassword)));
        userRepository.save(user);
        authTokenRepository.deleteBuToken(token);

    }

}
