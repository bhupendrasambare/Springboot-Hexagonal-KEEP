/**
 * author @bhupendrasambare
 * Date   :19/12/25
 * Time   :10:22 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secret;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    public long getAccessTokenExpiration() { return accessTokenExpiration; }
    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public long getRefreshTokenExpiration() { return refreshTokenExpiration; }
    public void setRefreshTokenExpiration(long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}
