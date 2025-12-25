package com.service.keep.domain.port.outbound;

public interface JwtTokenPort {

    String generateAccessToken(String userId);

    String generateRefreshToken(String userId);

    boolean validateToken(String token);

    String extractUserId(String token);
}
