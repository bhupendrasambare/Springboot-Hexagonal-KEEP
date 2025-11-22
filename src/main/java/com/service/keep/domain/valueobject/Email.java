/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:19 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;

@Getter
public class Email {

    private final String email;

    public Email(String email){
        if(StringUtil.isNullOrEmpty(email)){
            throw new IllegalArgumentException("Email value required");
        } else if(!email.contains("@")){
            throw new IllegalArgumentException("Invalid email value");
        }

        this.email = email;
    }
}
