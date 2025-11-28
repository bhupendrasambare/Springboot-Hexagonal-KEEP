/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:41 am
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.AuthUseCase;
import com.service.keep.domain.port.outbound.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;
    private final JwtTokenPort jwtToken;
    private final AuthTokenRepositoryPort authTokenRepository;
    private final EmailSenderPort emailSender;


    @Override
    public User signUp(String userName, String firstName, String lastName, String email, String password) {
        return null;
    }

    @Override
    public AuthToken login(String email, String password) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }

    @Override
    public AuthToken refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public void forgotPassword(String email) {

    }

    @Override
    public void resetPassword(String resetToken, String newPassword) {

    }


}
