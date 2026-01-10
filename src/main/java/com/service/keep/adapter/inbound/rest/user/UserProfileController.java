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
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileUseCase userProfileUseCase;

    @GetMapping("/profile")
    public UserResponse profile(){
        return this.userProfileUseCase.getUserProfile();
    }

    @PutMapping("/update")
    public UserResponse update(@RequestBody UpdateProfileRequest request) {
        return userProfileUseCase.updateProfile(request);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        userProfileUseCase.changePassword(request);
    }

}
