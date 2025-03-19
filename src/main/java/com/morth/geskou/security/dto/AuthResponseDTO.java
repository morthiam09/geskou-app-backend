package com.morth.geskou.security.dto;

public class AuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public AuthResponseDTO() {
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
