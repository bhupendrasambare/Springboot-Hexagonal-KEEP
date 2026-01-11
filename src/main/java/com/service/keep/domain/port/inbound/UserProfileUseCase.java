/**
 * author @bhupendrasambare
 * Date   :28/11/25
 * Time   :12:20 am
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.User;

public interface UserProfileUseCase {

    User getUserProfile(String userId);

    User updateProfile(String userId, String username, String firstName,String lastName);

    void changePassword(String userId, String oldPassword, String newPassword);

}
