/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.domain.rule;

import com.service.keep.domain.model.User;

public interface UserValidationRule {

    void validateForCreation(User user);

    void validateForUpdate(User user);
}
