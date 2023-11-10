package com.MBARI.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";

    public AuthResponseDto(TokenDto tokens) {
        this.accessToken = tokens.getAccessToken();
        this.refreshToken = tokens.getRefreshToken();
    }

}
