/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.user;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.AuthResult;
import com.service.keep.domain.port.inbound.AuthUseCase;
import com.service.keep.domain.port.outbound.JwtTokenPort;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public AuthResult signup(@Valid @RequestBody SignUpRequest request) {
        return authUseCase.signUp(request);
    }

    @PostMapping("/login")
    public AuthResult login(@Valid @RequestBody LoginRequest request) {
        return authUseCase.login(request);
    }

    @PostMapping("/refresh")
    public AuthResult refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return authUseCase.refresh(request);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest request) {
        authUseCase.logout(request);
    }

    @PostMapping("/forgot-password")
    public void forgot(@Valid @RequestBody ForgotPasswordRequest request) {
        authUseCase.forgotPassword(request);
    }

    @PostMapping("/reset-password")
    public void reset(@Valid @RequestBody ResetPasswordRequest request) {
        authUseCase.resetPassword(request);
    }
}
