/**
 * author @bhupendrasambare
 * Date   :27/03/26
 * Time   :12:24 am
 * Project:Keep
 **/
package com.service.keep.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private boolean status;

    private String message;

    private Object data;

    private String errorCode;
}
