package kr.kakaotech.community.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.kakaotech.community.dto.request.UserLoginResponse;
import kr.kakaotech.community.entity.User;
import kr.kakaotech.community.exception.CustomException;
import kr.kakaotech.community.exception.ErrorCode;
import kr.kakaotech.community.jwt.JwtProvider;
import kr.kakaotech.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${jwt.expirationtime.accessTtl}")
    private int accessTtl;
    @Value("${jwt.expirationtime.refreshTtl}")
    private int refreshTtl;

    private final String REFRESH_TOKEN_PREFIX = "refresh_token:";

    public UserLoginResponse getToken(String email, String password, HttpServletResponse response) {
        User user = userRepository.findByEmail(email).get();
//                .orElseThrow(() ->
//                new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!checkPassword(password, user)) {
            throw new CustomException(ErrorCode.BAD_PASSWORD);
        }

        // 기존 리프레시 토큰 무효화
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + user.getId().toString());

        // 토큰 발급
        TokenResponse tokenResponse = generateAndSaveToken(user);

        // 토큰 쿠키로 저장
        addTokenCookie(response, "accessToken", tokenResponse.accessToken, accessTtl);
        addTokenCookie(response, "refreshToken", tokenResponse.refreshToken, refreshTtl);

        return new UserLoginResponse(user.getNickname(), user.getEmail());
    }

    private boolean checkPassword(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public TokenResponse generateAndSaveToken(User user) {
        String accessToken = jwtProvider.createAccess(user.getId().toString(), user.getRole().toString());
        String refreshToken = jwtProvider.createRefresh(user.getId().toString(), user.getRole().toString());

        // Refresh 토큰만 Redis 저장
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + user.getId().toString(), refreshToken, refreshTtl, TimeUnit.SECONDS);

        return new TokenResponse(accessToken, refreshToken);
    }

    public void addTokenCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    public record TokenResponse(String accessToken, String refreshToken) {}
}
