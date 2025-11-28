/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:23 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;

import java.util.Objects;

public class HashedPassword {

    private final String value;

    public HashedPassword(String hashedPassword){
        if(StringUtil.isNullOrEmpty(hashedPassword)) {
            throw new IllegalArgumentException("Password hash cannot be empty");
        }

        this.value = hashedPassword;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteId)) return false;
        HashedPassword hashedPassword = (HashedPassword) o;
        return Objects.equals(value, hashedPassword.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
