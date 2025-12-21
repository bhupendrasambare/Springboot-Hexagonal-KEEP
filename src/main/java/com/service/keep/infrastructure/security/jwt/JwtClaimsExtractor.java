/**
 * author @bhupendrasambare
 * Date   :19/12/25
 * Time   :10:22 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtClaimsExtractor {

    private final JwtProperties properties;

    public JwtClaimsExtractor(JwtProperties properties) {
        this.properties = properties;
    }

    public Claims extract(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(
                        properties.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
