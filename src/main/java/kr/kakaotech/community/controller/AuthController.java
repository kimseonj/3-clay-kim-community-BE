package kr.kakaotech.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kakaotech.community.dto.ApiResponse;
import kr.kakaotech.community.dto.request.UserLoginResponse;
import kr.kakaotech.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    public ResponseEntity<ApiResponse<UserLoginResponse>> getTokenByLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return ApiResponse.success("로그인 성공", authService.getToken(email, password, response));
    }

}
