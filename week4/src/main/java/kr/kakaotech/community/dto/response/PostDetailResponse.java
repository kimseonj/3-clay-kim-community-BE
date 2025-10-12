package kr.kakaotech.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDetailResponse {
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
