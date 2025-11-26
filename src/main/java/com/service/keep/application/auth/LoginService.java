/**
 * author @bhupendrasambare
 * Date   :24/11/25
 * Time   :11:01 pm
 * Project:Keep
 **/
package com.service.keep.application.auth;

import com.service.keep.domain.model.User;
import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import com.service.keep.domain.port.outbound.TokenProviderPort;
import com.service.keep.domain.port.outbound.UserRepositoryPort;
import com.service.keep.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginService {

    private final UserRepositoryPort userRepository;

    private final PasswordHarsherPort passwordHarsher;

    private final TokenProviderPort tokenProvider;

    public String login(String email, String password){
        User user = userRepository.findByEmail(new Email(email)).orElseThrow(()->
                new IllegalArgumentException("Invalid credentials"));

        if(!passwordHarsher.matches(password, user.getPasswordHash().getHashedPassword())){
            throw new IllegalArgumentException("Invalid credentials");
        }

        return tokenProvider.generateToken(user.getId().getUserid());
    }

}
