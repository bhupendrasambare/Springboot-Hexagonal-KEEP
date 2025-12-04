/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:41 am
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.AuthResponse;
import com.service.keep.application.dto.response.TokenResponse;
import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.outbound.*;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import com.service.keep.application.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;
    private final JwtTokenPort jwtToken;
    private final AuthTokenRepositoryPort authTokenRepository;
    private final EmailSenderPort emailSender;


   public AuthResponse signUp(SignUpRequest request){
        if(userRepository.existsByEmail(new Email(request.getEmail()))){
            throw new IllegalArgumentException("Email id exists");
        }

        String userId = UUID.randomUUID().toString();
        String hashPass = passwordHarsher.hash(request.getPassword());

        User user = new User(
                new UserId(userId),
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                new Email(request.getEmail()),
                new HashedPassword(hashPass),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        User savedUser = userRepository.save(user);

        String access = jwtToken.generateAccessToken(savedUser.getId().getValue());
        String refreshToken = jwtToken.generateRefreshToken(savedUser.getId().getValue());

        AuthToken token = new AuthToken(
                refreshToken,
                savedUser.getId(),
                savedUser.getUsername(),
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(60 * 60 * 24 * 30)
        );

        TokenResponse response = TokenResponse.builder()
                .accessToken(access)
                .refreshToken(refreshToken)
                .build();

        return AuthResponse.builder()
                .user(UserMapper.toUserResponse(savedUser))
                .token(response)
                .build();

   }

   public TokenResponse login(LoginRequest request){
       Email email = new Email(request.getEmail());
       User user = userRepository.findByEmail(email).orElseThrow(
               () -> new IllegalArgumentException("Invalid credentials")
       );

       if(!passwordHarsher.matches(request.getPassword(), user.getPasswordHash().getValue())){
            throw new IllegalArgumentException("Invalid credentials");
       }

       String accessToken = jwtToken.generateAccessToken(user.getId().getValue());
       String refreshToken = jwtToken.generateRefreshToken(user.getId().getValue());

       AuthToken token = new AuthToken(
               refreshToken,
               user.getId(),
               user.getUsername(),
               LocalDateTime.now(),
               LocalDateTime.now().plusSeconds(60 * 60 * 24 * 30)
       );

       return TokenResponse.builder()
               .refreshToken(refreshToken)
               .accessToken(accessToken)
               .build();
   }

   public TokenResponse refresh(RefreshTokenRequest refreshToken){
        AuthToken authToken = authTokenRepository.findByToken(refreshToken.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if(authToken.isExpired()){
            authTokenRepository.deleteByToken(refreshToken.getRefreshToken());
            throw new IllegalArgumentException("Refresh token expired");
        }

        String newAccessToken = jwtToken.generateAccessToken(authToken.getUserId().getValue());
        String newRefreshToken = jwtToken.generateRefreshToken(authToken.getUserId().getValue());

        authTokenRepository.deleteByToken(refreshToken.getRefreshToken());
        AuthToken newToken = new AuthToken(
                newRefreshToken,
                authToken.getUserId(),
                authToken.getUsername(),
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(60 * 60 * 24 * 30)
        );

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

   }

   public void logout(RefreshTokenRequest request){
       authTokenRepository.deleteByToken(request.getRefreshToken());
   }

   public void forgotPassword(ForgotPasswordRequest request){
       User user = userRepository.findByEmail(new Email(request.getEmail()))
               .orElseThrow(() -> new IllegalArgumentException("User not found"));

       String randomToken = UUID.randomUUID().toString();
       AuthToken token = new AuthToken(
               randomToken,
               user.getId(),
               user.getUsername(),
               LocalDateTime.now(),
               LocalDateTime.now().plusMinutes(15)
       );

       authTokenRepository.save(token);

       emailSender.sendEmail(user.getEmail().getValue(), "Password reset","your reset token is TOKEN:"+randomToken);

   }

    public void resetPassword(ResetPasswordRequest req) {
        AuthToken reset = authTokenRepository.findByToken(req.getToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (reset.isExpired()) {
            authTokenRepository.deleteByToken(req.getToken());
            throw new IllegalArgumentException("Token expired");
        }

        User user = userRepository.findById(reset.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String hashed = passwordHarsher.hash(req.getNewPassword());
        user.changePassword(new HashedPassword(hashed));
        userRepository.save(user);

        authTokenRepository.deleteByToken(req.getToken());
    }

}
