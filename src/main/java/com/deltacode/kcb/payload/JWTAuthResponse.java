package com.deltacode.kcb.payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType;
    private String username;


    public JWTAuthResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
