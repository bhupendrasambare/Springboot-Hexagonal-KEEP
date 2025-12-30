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
import com.service.keep.application.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public UserResponse profile(){
        return this.userProfileService.getProfile();
    }

    @PutMapping("/update")
    public UserResponse update(@RequestBody UpdateProfileRequest request) {
        return userProfileService.updateProfile(request);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        userProfileService.updatePassword(request);
    }

}
