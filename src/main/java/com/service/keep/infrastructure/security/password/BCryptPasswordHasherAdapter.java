/**
 * author @bhupendrasambare
 * Date   :19/12/25
 * Time   :10:21 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.security.password;


import com.service.keep.domain.port.outbound.PasswordHarsherPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasherAdapter implements PasswordHarsherPort {

    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordHasherAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}

