package kr.kakaotech.community.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    private final SecretKey secretKey;
    private final int accessTtlSec;
    private final int refreshTtlSec;

    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.expirationtime.accessTime}") int accessTtlSec,
                       @Value("${jwt.expirationtime.refreshTime}") int refreshTtlSec) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessTtlSec = accessTtlSec;
        this.refreshTtlSec = refreshTtlSec;
    }

    // 검증
    public Claims parseToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
    public String getUserId(Claims claims) {
        return claims.getSubject();
    }
    public String getEmail(Claims claims) {
        return claims.get("email", String.class);
    }
    public String getRole(Claims claims) {
        return claims.get("role", String.class);
    }
    public boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
    public boolean isRefresh(Claims claims) {
        return claims.get("type").equals("refresh");
    }

    // 생성
    private String createAccess(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTtlSec))
                .signWith(secretKey)
                .compact();
    }

    private String createRefresh(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .claim("typ", "refresh")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTtlSec))
                .id(UUID.randomUUID().toString())
                .signWith(secretKey)
                .compact();
    }
}
