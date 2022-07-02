package com.java.demo.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private Boolean authenticated;
    private String tokenPrefix = "Bearer";
    private String token;

    public TokenResponse(Boolean authenticated, String token){
        this.authenticated = authenticated;
        this.token = token;
    }

}
