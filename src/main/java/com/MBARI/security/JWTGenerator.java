package com.MBARI.security;

import com.MBARI.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateRefreshToken(String username) {
        Date currentDate = new Date();
        Date refreshExpireDate = new Date(currentDate.getTime() + SecurityConstants.REFRESH_TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(refreshExpireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public TokenDto generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date accessExpireDate = new Date(currentDate.getTime() + SecurityConstants.ACCESS_TOKEN_EXPIRATION);

        String accessToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(accessExpireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        String refreshToken = generateRefreshToken(username);
        TokenDto tokens = new TokenDto();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);
        return tokens;
    }

    public String refreshAccessToken(String refreshToken) {
        String username = getUsernameFromJWT(refreshToken);
        Date currentDate = new Date();
        Date accessExpireDate = new Date(currentDate.getTime() + SecurityConstants.ACCESS_TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(accessExpireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect", ex.fillInStackTrace());
        }
    }

}
