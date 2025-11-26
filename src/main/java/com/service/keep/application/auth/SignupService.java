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
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class SignupService implements ManageAuthUseCase {

    private final UserRepositoryPort userRepository;

    private final PasswordHarsherPort passwordHarsher;


    @Override
    public User signup(String email, String userName, String firstName, String lastName, String name, String password) {
        if(userRepository.findByUserName(userName).isPresent()){
            throw new IllegalArgumentException("UserName already exists");
        }
        if(userRepository.findByEmail(new Email(email)).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(
                new UserId(UUID.randomUUID().toString()),
                userName,
                firstName,
                lastName,
                new Email(email),
                new HashedPassword(password),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        throw new UnsupportedOperationException("login handled by LoginService");
    }

    @Override
    public boolean logout(String token) {
        throw new UnsupportedOperationException("logout handled by LogoutService");
    }

    @Override
    public boolean requestPasswordReset(String email) {
        throw new UnsupportedOperationException("handled by ForgotPasswordService");
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        throw new UnsupportedOperationException("handled by ResetPasswordService");
    }
}
