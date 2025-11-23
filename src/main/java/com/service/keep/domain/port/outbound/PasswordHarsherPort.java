/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:17 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

public interface PasswordHarsherPort {

    String hash(String warPassword);

    boolean matches(String rawPassword, String hashedPassword);

}
