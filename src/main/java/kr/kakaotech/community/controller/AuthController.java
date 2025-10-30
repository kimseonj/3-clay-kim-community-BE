package kr.kakaotech.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kakaotech.community.dto.ApiResponse;
import kr.kakaotech.community.dto.request.UserLoginRequest;
import kr.kakaotech.community.dto.request.UserLoginResponse;
import kr.kakaotech.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<ApiResponse<UserLoginResponse>> getTokenByLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        UserLoginResponse userLoginResponse = authService.getToken(userLoginRequest.getEmail(), userLoginRequest.getPassword(), response);
        return ApiResponse.success("로그인 성공", userLoginResponse);
    }

}
