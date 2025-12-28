/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.user;

import com.service.keep.application.dto.request.SignUpRequest;
import com.service.keep.application.dto.response.AuthResponse;
import com.service.keep.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request){
        return this.authService.signUp(request);
    }

}
