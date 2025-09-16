package com.eventapp.auth_service.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
    }

    private String token;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }


}
