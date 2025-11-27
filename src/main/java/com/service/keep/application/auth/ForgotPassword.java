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
import com.service.keep.domain.port.outbound.EmailSenderPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class ForgotPassword {

    private final UserRepositoryPort userRepository;
    private final AuthTokenRepositoryPort authTokenRepository;
    private final EmailSenderPort emailSender;

    public void requestPasswordReset(String email){
        User user = this.userRepository.findByEmail(new Email(email)).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        String token = UUID.randomUUID().toString();

        AuthToken authToken = new AuthToken(
                user.getId(),user.getUsername(),
                token, LocalDateTime.now(), LocalDateTime.now()
        );
        authTokenRepository.save(authToken);

        this.emailSender.sendEmail(email, "Reset Password", "Your token is :"+token);
    }

}
