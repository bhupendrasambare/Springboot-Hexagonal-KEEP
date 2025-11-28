/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:19 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;

import java.util.Objects;

public class Email {

    private final String value;

    public Email(String email){
        if(StringUtil.isNullOrEmpty(email)){
            throw new IllegalArgumentException("Email value required");
        } else if(!email.contains("@")){
            throw new IllegalArgumentException("Invalid email value");
        }

        this.value = email;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteId)) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
