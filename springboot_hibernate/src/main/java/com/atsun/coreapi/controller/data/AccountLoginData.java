package com.atsun.coreapi.controller.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter

@Setter

@NoArgsConstructor

public class AccountLoginData implements Serializable {

    private static final long serialVersionUID = -8924146362285483685L;

    /**
     * Token
     */
    private String token;

    public AccountLoginData(String token) {
        this.token = token;
    }

}
