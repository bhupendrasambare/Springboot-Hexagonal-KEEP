/**
 * author @bhupendrasambare
 * Date   :02/12/25
 * Time   :10:30 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;



}
