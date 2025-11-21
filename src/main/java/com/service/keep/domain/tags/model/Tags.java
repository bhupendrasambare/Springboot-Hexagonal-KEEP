/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:55 pm
 * Project:Keep
 **/
package com.service.keep.domain.tags.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    private String id;

    private String color;

    private String imageUri;

    private String userId;

    private String userName;
}
