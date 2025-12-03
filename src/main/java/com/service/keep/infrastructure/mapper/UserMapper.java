/**
 * author @bhupendrasambare
 * Date   :03/12/25
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.mapper;

import com.service.keep.application.dto.request.SignUpRequest;
import com.service.keep.application.dto.response.UserResponse;
import com.service.keep.domain.model.User;
import com.service.keep.domain.valueobject.Email;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;

import java.time.LocalDateTime;

public class UserMapper {

    private UserMapper(){}

    public static User toDomain(SignUpRequest request, String generatedId, String hashedPassword){
        return new User(
                new UserId(generatedId),
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                new Email(request.getEmail()),
                new HashedPassword(hashedPassword),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId().getValue())
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail().getValue())
                .build();
    }
}
