/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:17 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.User;

public interface ManageUserUseCase {

    User createUser(User user);

    User updateUser(User user, String userId);

    User getUserDetails(String userId);

    boolean changePassword(String userId, String oldPassword, String newPassword);

    boolean deleteUser(String userId);


}
