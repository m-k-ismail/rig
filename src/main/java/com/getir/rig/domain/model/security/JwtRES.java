package com.getir.rig.domain.model.security;

public class JwtRES {

    private final String token;

    public JwtRES(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
