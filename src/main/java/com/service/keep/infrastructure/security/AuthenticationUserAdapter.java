/**
 * author @bhupendrasambare
 * Date   :19/12/25
 * Time   :10:22 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.security;

import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import com.service.keep.domain.valueobject.UserId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUserAdapter implements AuthenticatedUserPort {

    @Override
    public UserId getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new UserId(auth.getName());
    }
}
