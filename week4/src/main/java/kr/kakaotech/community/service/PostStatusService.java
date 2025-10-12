package kr.kakaotech.community.service;

import kr.kakaotech.community.entity.Post;
import kr.kakaotech.community.entity.PostStatus;
import kr.kakaotech.community.exception.CustomException;
import kr.kakaotech.community.exception.ErrorCode;
import kr.kakaotech.community.repository.PostRepository;
import kr.kakaotech.community.repository.PostStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostStatusService {

    private final RedisTemplate<String, String> redisTemplate;
    private final PostRepository postRepository;
    private final PostStatusRepository postStatusRepository;

    private static final String VIEW_KEY_PREFIX = "post:view:";
    private static final String LIKE_KEY_PREFIX = "post:like:";
    private static final String COMMENT_KEY_PREFIX = "post:comment:";

    /**
     * 조회수 증가
     */
    public void incrementViewCount(int postId) {
        String key = VIEW_KEY_PREFIX + postId;
        redisTemplate.opsForValue().increment(key);
    }

    /**
     * 좋아요 증가
     */
    public void incrementLikeCount(int postId) {
        String key = LIKE_KEY_PREFIX + postId;
        redisTemplate.opsForValue().increment(key);
    }

    /**
     * 좋아요 감소
     */
    public void decrementLikeCount(int postId) {
        String key = LIKE_KEY_PREFIX + postId;
        redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 댓글 수 추가
     */
    public void incrementCommentCount(int postId) {
        String key = COMMENT_KEY_PREFIX + postId;
        redisTemplate.opsForValue().increment(key);
    }

    /**
     * Redis → MySQL 동기화
     */
    @Transactional
    public void syncToDatabase(int postId) {
        String viewKey = VIEW_KEY_PREFIX + postId;
        String likeKey = LIKE_KEY_PREFIX + postId;
        String commentKey = COMMENT_KEY_PREFIX + postId;

        int viewCount = parseInteger(redisTemplate.opsForValue().get(viewKey));
        int likeCount = parseInteger(redisTemplate.opsForValue().get(likeKey));
        int commentCount = parseInteger(redisTemplate.opsForValue().get(commentKey));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        PostStatus postStatus = postStatusRepository.findById(postId).orElseGet(() -> new PostStatus(post));

        postStatus.updateCount(viewCount, likeCount, commentCount);

        // DB에 값 누적 업데이트
        postStatusRepository.save(postStatus);

        // 동기화 후 Redis 초기화
        redisTemplate.delete(viewKey);
        redisTemplate.delete(likeKey);
        redisTemplate.delete(commentKey);
    }

    private int parseInteger(String value) {
        if (value == null || value.isEmpty()) return 0;
        return Integer.parseInt(value);
    }
}

