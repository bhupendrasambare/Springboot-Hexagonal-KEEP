/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:23 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;

@Getter
public class HashedPassword {

    private final String hashedPassword;

    public HashedPassword(String hashedPassword){
        if(StringUtil.isNullOrEmpty(hashedPassword)) {
            throw new IllegalArgumentException("Password hash cannot be empty");
        }

        this.hashedPassword = hashedPassword;
    }
}
