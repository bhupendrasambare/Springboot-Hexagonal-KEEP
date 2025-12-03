/**
 * author @bhupendrasambare
 * Date   :02/12/25
 * Time   :10:30 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.application.dto.request.ChangePasswordRequest;
import com.service.keep.application.dto.request.UpdateProfileRequest;
import com.service.keep.application.dto.response.UserResponse;
import com.service.keep.domain.model.User;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import com.service.keep.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;


    public UserResponse getProfile(String userId){
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return UserMapper.toUserResponse(user);

    }

    public UserResponse updateProfile(String userId, UpdateProfileRequest request){
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.updateProfile(request.getUserName(),request.getFirstName(), request.getLastName());
        User updatedUser = userRepository.save(user);

        return UserMapper.toUserResponse(updatedUser);
    }

    public void updatePassword(String userId, ChangePasswordRequest request){
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!passwordHarsher.matches(request.getOldPassword(), user.getPasswordHash().getValue())){
            throw new IllegalArgumentException("Invalid password");
        }

        user.changePassword(new HashedPassword(passwordHarsher.hash(request.getNewPassword())));
        userRepository.save(user);

    }


}
