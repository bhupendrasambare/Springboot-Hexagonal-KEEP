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
import com.service.keep.application.response.Response;
import com.service.keep.application.response.ResponseUtil;
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> profile() {

        UserResponse response = UserMapper.toUserResponse(
                userProfileUseCase.getUserProfile(getUserId())
        );

        return ResponseUtil.success("Profile fetched successfully", response);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> update(@RequestBody UpdateProfileRequest request) {

        UserResponse response = UserMapper.toUserResponse(
                userProfileUseCase.updateProfile(
                        getUserId(),
                        request.getUserName(),
                        request.getFirstName(),
                        request.getLastName()
                )
        );

        return ResponseUtil.success("Profile updated successfully", response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Response> changePassword(@RequestBody ChangePasswordRequest request) {

        userProfileUseCase.changePassword(
                getUserId(),
                request.getOldPassword(),
                request.getNewPassword()
        );

        return ResponseUtil.success("Password changed successfully");
    }
}
