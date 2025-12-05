/**
 * author @bhupendrasambare
 * Date   :04/12/25
 * Time   :11:58 pm
 * Project:Keep
 **/
package com.service.keep.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagCreateRequest {
    @NotBlank
    private String name;
    private String color;
    private String imageUri;
}
