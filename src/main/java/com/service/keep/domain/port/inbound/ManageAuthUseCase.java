/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.User;

public interface ManageAuthUseCase {

    User signup(String email, String username, String name, String password);

    String login(String email, String password);

    boolean logout(String token);

    boolean requestPasswordReset(String email);

    boolean resetPassword(String resetToken, String newPassword);

}
