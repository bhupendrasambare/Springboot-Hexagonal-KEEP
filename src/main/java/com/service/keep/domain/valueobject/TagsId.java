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
public class TagsId {

    private final String userid;

    public TagsId(String userid){
        if(StringUtil.isNullOrEmpty(userid)){
            throw new IllegalArgumentException("TagId cannot be empty");
        }

        this.userid = userid;
    }
}
