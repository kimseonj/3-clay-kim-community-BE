package kr.kakaotech.community.dto.response;

import kr.kakaotech.community.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSummaryResponse {
    private int id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;

    public static PostSummaryResponse fromEntity(Post post) {
        return new PostSummaryResponse(
                post.getId(),
                post.getTitle(),
                post.getNickname(),
                post.getCreatedAt()
        );
    }
}
