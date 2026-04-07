/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:38 am
 * Project:Keep
 **/
package com.service.keep.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OllamaRequest {
    private String model;
    private String prompt;
    private boolean stream;
}
