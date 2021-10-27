package com.atsun.coreapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;

@Getter
@Setter
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 8523854101043037809L;

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
