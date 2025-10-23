package kr.kakaotech.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDetailResponse {
    String id;
    String email;
    String nickname;
    String url;
    Boolean deleted;
    String role;
}
