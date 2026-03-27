/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.user;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.AuthResult;
import com.service.keep.application.response.Response;
import com.service.keep.application.response.ResponseUtil;
import com.service.keep.domain.port.inbound.AuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@Valid @RequestBody SignUpRequest request) {
        AuthResult result = authUseCase.signUp(request);
        return ResponseUtil.success("User registered successfully", result);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = authUseCase.login(request);
        return ResponseUtil.success("Login successful", result);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResult result = authUseCase.refresh(request);
        return ResponseUtil.success("Token refreshed successfully", result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@Valid @RequestBody RefreshTokenRequest request) {
        authUseCase.logout(request);
        return ResponseUtil.success("Logout successful");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgot(@Valid @RequestBody ForgotPasswordRequest request) {
        authUseCase.forgotPassword(request);
        return ResponseUtil.success("Password reset link sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> reset(@Valid @RequestBody ResetPasswordRequest request) {
        authUseCase.resetPassword(request);
        return ResponseUtil.success("Password reset successfully");
    }
}