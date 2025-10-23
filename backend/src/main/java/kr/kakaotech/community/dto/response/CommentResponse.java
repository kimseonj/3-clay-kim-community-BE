package kr.kakaotech.community.dto.response;

import kr.kakaotech.community.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private int id;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;

    public static CommentResponse fromEntity(Comment comment) {
        CommentResponse response;
        if (comment.getDeleted()) {
            response = new CommentResponse(
                    comment.getId(),
                    "삭제된 댓글입니다.",
                    null,
                    comment.getCreatedAt()
            );
        } else {
            response = new CommentResponse(
                    comment.getId(),
                    comment.getContent(),
                    comment.getUser().getNickname(),
                    comment.getCreatedAt()
            );
        }

        return response;
    }
}
