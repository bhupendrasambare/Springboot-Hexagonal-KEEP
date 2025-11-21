/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:38 pm
 * Project:Keep
 **/
package com.service.keep.domain.user.service;

import com.service.keep.domain.user.model.User;

public interface UserDomainService {

    boolean checkPasswordMatch();

    String hashPassword();

    User updateProfile();

    boolean canResetPassword();

}
