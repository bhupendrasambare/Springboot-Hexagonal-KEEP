/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:41 am
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.AuthResult;
import com.service.keep.application.dto.response.TokenResponse;
import com.service.keep.application.exception.*;
import com.service.keep.application.mapper.UserMapper;
import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.AuthUseCase;
import com.service.keep.domain.port.outbound.*;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthUseCaseService implements AuthUseCase, UserDetailsService {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;
    private final JwtTokenPort jwtToken;
    private final AuthTokenRepositoryPort authTokenRepository;
    private final EmailSenderPort emailSender;

    @Override
    public AuthResult signUp(SignUpRequest request) {

        if (userRepository.existsByEmail(new Email(request.getEmail()))) {
            throw new EmailAlreadyExistsException();
        }

        HashedPassword hashed =
                new HashedPassword(passwordHarsher.hash(request.getPassword()));

        User user = new User(
                new UserId(UUID.randomUUID().toString()),
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                new Email(request.getEmail()),
                hashed,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        userRepository.save(user);

        String access = jwtToken.generateAccessToken(user.getId().getValue());
        String refresh = jwtToken.generateRefreshToken(user.getId().getValue());

        authTokenRepository.save(
                new AuthToken(refresh, user.getId(), user.getUsername(),
                        LocalDateTime.now(), LocalDateTime.now().plusDays(30))
        );

        return AuthResult.builder()
                .user(UserMapper.toUserResponse(user))
                .token(TokenResponse.builder()
                        .accessToken(access)
                        .refreshToken(refresh)
                        .build())
                .build();
    }

    @Override
    public AuthResult login(LoginRequest request) {

        User user = userRepository.findByEmail(new Email(request.getEmail()))
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordHarsher.matches(request.getPassword(), user.getPasswordHash().getValue())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtToken.generateAccessToken(user.getId().getValue());
        String refreshToken = jwtToken.generateRefreshToken(user.getId().getValue());

        authTokenRepository.save(
                new AuthToken(
                        refreshToken,
                        user.getId(),
                        user.getUsername(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(30)
                )
        );

        return AuthResult.builder()
                .user(UserMapper.toUserResponse(user))
                .token(TokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build())
                .build();
    }


    @Override
    public AuthResult refresh(RefreshTokenRequest request) {

        AuthToken token = authTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(InvalidTokenException::new);

        if (token.isExpired()) {
            authTokenRepository.deleteByToken(request.getRefreshToken());
            throw new TokenExpiredException();
        }

        User user = userRepository.findById(token.getUserId())
                .orElseThrow(UserNotFoundException::new);

        String newAccessToken = jwtToken.generateAccessToken(user.getId().getValue());

        return AuthResult.builder()
                .user(UserMapper.toUserResponse(user))
                .token(TokenResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(request.getRefreshToken())
                        .build())
                .build();
    }


    @Override
    public void logout(RefreshTokenRequest request) {
        authTokenRepository.deleteByToken(request.getRefreshToken());
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(new Email(request.getEmail()))
                .orElseThrow(UserNotFoundException::new);

        String token = UUID.randomUUID().toString();
        authTokenRepository.save(
                new AuthToken(token, user.getId(), user.getUsername(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(15))
        );

        emailSender.sendEmail(
                user.getEmail().getValue(),
                "Password Reset",
                "Reset token: " + token
        );
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        AuthToken reset = authTokenRepository.findByToken(request.getToken())
                .orElseThrow(InvalidTokenException::new);

        if (reset.isExpired()) {
            authTokenRepository.deleteByToken(request.getToken());
            throw new TokenExpiredException();
        }

        User user = userRepository.findById(reset.getUserId())
                .orElseThrow(UserNotFoundException::new);

        user.changePassword(new HashedPassword(request.getNewPassword()));
        userRepository.save(user);
        authTokenRepository.deleteByToken(request.getToken());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(new UserId(username))
                .orElseThrow(UserNotFoundException::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getId().getValue())
                .password(user.getPasswordHash().getValue())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
