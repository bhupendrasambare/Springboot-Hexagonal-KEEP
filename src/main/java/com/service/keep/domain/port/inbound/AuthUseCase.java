package com.service.keep.domain.port.inbound;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.AuthResult;
import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;

public interface AuthUseCase {

    AuthResult signUp(SignUpRequest request);

    AuthResult login(LoginRequest request);

    AuthResult loginWithGoogle(Email email, String name);

    AuthResult refresh(RefreshTokenRequest request);

    void logout(RefreshTokenRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);
}
