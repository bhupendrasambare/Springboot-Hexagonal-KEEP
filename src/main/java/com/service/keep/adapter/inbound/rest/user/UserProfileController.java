/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.user;

import com.service.keep.application.dto.request.ChangePasswordRequest;
import com.service.keep.application.dto.request.UpdateProfileRequest;
import com.service.keep.application.dto.response.UserResponse;
import com.service.keep.application.mapper.UserMapper;
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileUseCase userProfileUseCase;

    private final AuthenticatedUserPort authenticatedUserPort;

    private String getUserId() {
        return authenticatedUserPort.getCurrentUserId().getValue();
    }

    @GetMapping("/profile")
    public UserResponse profile() {
        return UserMapper.toUserResponse(
                userProfileUseCase.getUserProfile(getUserId())
        );
    }

    @PutMapping("/update")
    public UserResponse update(@RequestBody UpdateProfileRequest request) {

        return UserMapper.toUserResponse(
                userProfileUseCase.updateProfile(
                        getUserId(),
                        request.getUserName(),
                        request.getFirstName(),
                        request.getLastName()
                )
        );
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {

        userProfileUseCase.changePassword(
                getUserId(),
                request.getOldPassword(),
                request.getNewPassword()
        );
    }
}
