/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:25 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;

import java.util.Objects;

public class ReminderId {

    private final String value;

    public ReminderId(String userid){
        if(StringUtil.isNullOrEmpty(userid)){
            throw new IllegalArgumentException("NoteId cannot be empty");
        }

        this.value = userid;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReminderId)) return false;
        ReminderId reminderId = (ReminderId) o;
        return Objects.equals(value, reminderId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
