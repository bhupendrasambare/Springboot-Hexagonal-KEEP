package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.AuthToken;
import com.service.keep.domain.model.User;

public interface AuthUseCase {

    User signUp(String userName, String firstName, String lastName, String email, String password);

    AuthToken login(String email, String password);

    void logout(String refreshToken);

    AuthToken refreshToken(String refreshToken);

    void forgotPassword(String email);

    void resetPassword(String resetToken, String newPassword);

}
