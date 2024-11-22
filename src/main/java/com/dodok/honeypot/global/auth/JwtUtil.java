package com.dodok.honeypot.global.auth;

import com.dodok.honeypot.domain.auth.error.AuthErrorCode;
import com.dodok.honeypot.global.error.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
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
    public String createAccessToken(Long memberId, String role) {
        LocalDateTime localDate = LocalDateTime.now();
        return Jwts.builder()
                .setHeaderParam("type", "accessToken")
                .claim("memberId", memberId)
                .claim("role",role)
                .setIssuedAt(Timestamp.valueOf(localDate))
                .setExpiration(Timestamp.valueOf(localDate.plusHours(ACCESSTOKEN_VALIDATE_TIME)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // refresh token 발급
    public String createRefreshToken(){
        LocalDateTime localDate = LocalDateTime.now();
        return Jwts.builder()
                .setHeaderParam("type", "refreshToken")
                .setIssuedAt(Timestamp.valueOf(localDate))
                .setExpiration(Timestamp.valueOf(localDate.plusHours(REFRESHTOKEN_VALIDATE_TIME)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    public Long getMemberIdFromAccessToken(String accessToken){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .get("memberId", Long.class);
    }

    public Long getMemberIdFromAuthorizationHeader(String authorizationHeader) {
        String accessToken = authorizationHeader.substring(7);
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .get("memberId", Long.class);
    }
    public void verifyMemberRefreshToken(String repositoryRefreshToken, String memberRefreshToken) {
        if (!(repositoryRefreshToken.equals(memberRefreshToken)))
            throw new UnauthorizedException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
