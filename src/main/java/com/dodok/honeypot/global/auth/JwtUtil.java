package com.dodok.honeypot.global.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.accessToken-validate-time}")
    private long ACCESSTOKEN_VALIDATE_TIME;

    @Value("${jwt.refreshToken-validate-time}")
    private long REFRESHTOKEN_VALIDATE_TIME;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // access token 발급
    public String createAccessToken(Long userId, String role) {
        LocalDateTime localDate = LocalDateTime.now();
        return Jwts.builder()
                .setHeaderParam("type", "accessToken")
                .claim("userId", userId)
                .claim("role",role)
                .setIssuedAt(Timestamp.valueOf(localDate))
                .setExpiration(Timestamp.valueOf(localDate.plusHours(ACCESSTOKEN_VALIDATE_TIME)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // refresh token 발급
    public String createRefreshToken(){
        LocalDateTime localDate = LocalDateTime.now();
        return Jwts.builder()
                .setHeaderParam("type", "refreshToken")
                .setIssuedAt(Timestamp.valueOf(localDate))
                .setExpiration(Timestamp.valueOf(localDate.plusHours(REFRESHTOKEN_VALIDATE_TIME)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact(); // JWT 토큰 생성
    }
}
