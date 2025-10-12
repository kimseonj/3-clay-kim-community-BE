package kr.kakaotech.community.controller;

import kr.kakaotech.community.dto.ApiResponse;
import kr.kakaotech.community.dto.request.UserRegisterRequest;
import kr.kakaotech.community.dto.request.UserUpdateRequest;
import kr.kakaotech.community.dto.response.UserDetailResponse;
import kr.kakaotech.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/users")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRegisterRequest userDto) {
        userService.registerUser(userDto);

        ApiResponse response = new ApiResponse("회원가입 성공", userDto.getEmail());
        return ResponseEntity.status(201).body(response);
    }

    /**
     * 특정 회원 불러오기
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(@PathVariable String userId) {
        UserDetailResponse user = userService.getUser(userId);

        ApiResponse<UserDetailResponse> response = new ApiResponse<>("단일 회원 조회 성공", user);
        return ResponseEntity.status(200).body(response);
    }

    /**
     * 회원 리스트 불러오기
     */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Page<UserDetailResponse>>> getUserList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<UserDetailResponse> userPage = userService.getUserPage(pageable);

        ApiResponse<Page<UserDetailResponse>> response = new ApiResponse<>("모든 회원 불러오기 성공", userPage);
        return ResponseEntity.status(200).body(response);
    }

    /**
     * 회원 정보 업데이트
     */
    @PatchMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserDetailResponse userDetailResponse = userService.updateUser(userId, userUpdateRequest);

        ApiResponse<UserDetailResponse> apiResponse = new ApiResponse<>("업데이트 성공", userDetailResponse);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * 회원 탈퇴 - soft delete
     */
    @PatchMapping("/users/{userId}/deactivation")
    public void deleteUser(@PathVariable String userId) {
        userService.softDeleteUser(userId);
    }
}
