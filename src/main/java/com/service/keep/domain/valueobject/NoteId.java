/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:25 pm
 * Project:Keep
 **/
package com.service.keep.domain.valueobject;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;

@Getter
public class NoteId {

    private final String userid;

    public NoteId(String userid){
        if(StringUtil.isNullOrEmpty(userid)){
            throw new IllegalArgumentException("NoteId cannot be empty");
        }

        this.userid = userid;
    }
}
