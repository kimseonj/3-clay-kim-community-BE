package kr.kakaotech.community.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String email;
    private String nickname;
    private String password;
    private String url;
}
