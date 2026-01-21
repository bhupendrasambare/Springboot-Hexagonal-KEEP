/**
 * author @bhupendrasambare
 * Date   :02/12/25
 * Time   :10:30 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.model.User;
import com.service.keep.domain.port.inbound.UserProfileUseCase;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.HashedPassword;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHarsherPort passwordHarsher;

    @Override
    public User getUserProfile(String userId) {
        return userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User updateProfile(String userId, String username, String firstName, String lastName) {

        User user = getUserProfile(userId);
        user.updateProfile(username, firstName, lastName);

        return userRepository.save(user);
    }

    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {

        User user = getUserProfile(userId);

        if (!passwordHarsher.matches(oldPassword, user.getPasswordHash().getValue())) {
            throw new IllegalArgumentException("Invalid password");
        }

        user.changePassword(
                new HashedPassword(passwordHarsher.hash(newPassword))
        );

        userRepository.save(user);
    }
}
