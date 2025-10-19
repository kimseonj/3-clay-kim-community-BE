package kr.kakaotech.community.global.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kakaotech.community.entity.User;
import kr.kakaotech.community.entity.UserRole;
import kr.kakaotech.community.exception.CustomException;
import kr.kakaotech.community.exception.ErrorCode;
import kr.kakaotech.community.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 현재 요청 경로 추출
        String requestURI = request.getRequestURI();

        // request 에서 Authorization 헤더 추출
        log.info("[JwtFilter] 요청 URI: {}", requestURI);
        String accessToken = request.getHeader("access");

        // Authorization 검증
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 검증
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_JWT);
        }

        // 토큰에서 email 과 role 획득
        UUID userId = UUID.fromString(jwtUtil.getUserId(accessToken));
        String email = jwtUtil.getEmail(accessToken);
        UserRole userRole = UserRole.valueOf(jwtUtil.getRole(accessToken).toUpperCase());

        // userEntity 생성
        User user = User.builder()
                .id(userId)
                .email(email)
                .role(userRole)
                .build();

        // UserDetails 에 회원정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        // 스프링 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // contextHolder 에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
