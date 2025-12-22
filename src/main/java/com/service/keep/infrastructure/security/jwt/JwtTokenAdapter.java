/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:27 am
 * Project:Keep
 **/
package com.service.keep.infrastructure.security.jwt;

import com.service.keep.domain.port.outbound.JwtTokenPort;
import com.service.keep.domain.valueobject.UserId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenAdapter implements JwtTokenPort {


    private final JwtProperties properties;
    private final SecretKey secretKey;

    public JwtTokenAdapter(JwtProperties properties) {
        this.properties = properties;
        this.secretKey = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
    }

    @Override
    public String generateAccessToken(String userId) {
        return buildToken(new UserId(userId), properties.getAccessTokenExpiration());
    }

    @Override
    public String generateRefreshToken(String userId) {
        return buildToken(new UserId(userId), properties.getRefreshTokenExpiration());
    }

    @Override
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public String extractUserId(String token) {
        Claims claims = extractClaims(token);
        return new UserId(claims.getSubject()).getValue();
    }

    private String buildToken(UserId userId, long expirationMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(userId.getValue())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
