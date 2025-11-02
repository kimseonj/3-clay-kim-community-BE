package kr.kakaotech.community.auth;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface AuthenticationStrategy {
    Optional<String> extractAuth(HttpServletRequest request);
    void setAttributeByAuth(String auth, HttpServletRequest request);
}
