/**
 * author @bhupendrasambare
 * Date   :24/11/25
 * Time   :11:01 pm
 * Project:Keep
 **/
package com.service.keep.application.auth;

import com.service.keep.domain.port.outbound.TokenProviderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutService {

    private final TokenProviderPort tokenProviderPort;

    public void logout(String token){

    }

}
