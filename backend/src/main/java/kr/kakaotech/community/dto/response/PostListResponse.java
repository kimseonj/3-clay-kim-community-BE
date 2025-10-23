package kr.kakaotech.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private List<PostSummaryResponse> posts;
    private Integer nextCursor;
    private boolean hasNext;
}
