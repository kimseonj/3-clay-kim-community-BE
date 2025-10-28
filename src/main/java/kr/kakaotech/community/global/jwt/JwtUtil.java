package kr.kakaotech.community.global.jwt;

import io.jsonwebtoken.Jwts;
import kr.kakaotech.community.dto.request.JwtExpirationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final JwtExpirationProperties jwtExpirationProperties;

    public JwtUtil(@Value("${jwt.secret}") String secretKey, JwtExpirationProperties jwtExpirationProperties) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.jwtExpirationProperties = jwtExpirationProperties;
    }

    // 검증
    public String getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
    }
    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }
    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // 생성
    private String createJwt(String userId, String email, String role, Long expiredTime) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(secretKey)
                .compact();
    }

    public String createAccessToken(String userId, String email, String role) {
        return createJwt(userId, email, role, jwtExpirationProperties.getAccessTime()); // jwtExpirationProperties.getAccess());
    }
}
