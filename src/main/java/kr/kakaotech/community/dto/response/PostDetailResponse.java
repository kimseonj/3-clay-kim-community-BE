package kr.kakaotech.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PostDetailResponse {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private UUID userId;
    private String nickname;
    private String profileImageUrl;
}
