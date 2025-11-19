/**
 * author @bhupendrasambare
 * Date   :17/11/25
 * Time   :10:38 pm
 * Project:Keep
 **/
package com.service.keep.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("restToken")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetToken {

    @Id
    private String id;

    private String user_id;
    private String username;
    private String token;
    private String type;

    private LocalDateTime createdDate;
    private LocalDateTime expiryDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
