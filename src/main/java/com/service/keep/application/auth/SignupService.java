/**
 * author @bhupendrasambare
 * Date   :24/11/25
 * Time   :11:01 pm
 * Project:Keep
 **/
package com.service.keep.application.auth;

import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.ManageAuthUseCase;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class SignupService implements ManageAuthUseCase {

    private final UserRepositoryPort userRepository;

    private final PasswordHarsherPort passwordHarsher;


    @Override
    public User signup(String email, String username, String name, String password) {
        if(userRepository.findByEmail(new Email(email)).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(
                new UserId(UUID.randomUUID().toString()),
                new Email(email),
        )

    }

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        return false;
    }

    @Override
    public boolean resetPassword(String resetToken, String newPassword) {
        return false;
    }
}
