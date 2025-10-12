package kr.kakaotech.community.service;

import kr.kakaotech.community.dto.request.PostRegisterRequest;
import kr.kakaotech.community.dto.response.PostDetailResponse;
import kr.kakaotech.community.dto.response.PostListResponse;
import kr.kakaotech.community.dto.response.PostSummaryResponse;
import kr.kakaotech.community.entity.Image;
import kr.kakaotech.community.entity.Post;
import kr.kakaotech.community.entity.PostImage;
import kr.kakaotech.community.entity.User;
import kr.kakaotech.community.exception.CustomException;
import kr.kakaotech.community.exception.ErrorCode;
import kr.kakaotech.community.repository.PostRepository;
import kr.kakaotech.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * Post 등록
     */
    @Transactional
    public void registerPost(String userId, PostRegisterRequest request) {
        User getUser = userRepository.findById(UUID.fromString(userId)).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_USER));

        Post post = Post.toEntity(request, getUser);

        if (request.getUrlList() != null) {
            for (String url : request.getUrlList()) {
                Image image = new Image(url);
                PostImage postImage = new PostImage(image);
                post.saveImage(postImage);
            }
        }

        postRepository.save(post);
    }

    /**
     * 게시글 수정 버튼 권한 체크
     */
    @Transactional(readOnly = true)
    public boolean checkAuthorization(int postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POST));

        return post.getUser().getId().toString().equals(userId);
    }

    /**
     * 게시글 내용 수정
     */
    @Transactional
    public void updatePost(int postId, String userId, PostRegisterRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POST));

        if (!post.getUser().getId().toString().equals(userId)) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        post.updatePost(request);
    }

    /**
     * 게시글 목록 조회
     */
    @Transactional
    public PostListResponse getPostList(Integer cursor, int size) {
        List<Post> posts;

        if (cursor == null) {
            posts = postRepository.findTopPost(size);
        } else {
            posts = postRepository.findPostByCursor(cursor, size);
        }

        boolean hasNext = posts.size() == size;
        Integer nextCursor = hasNext ? posts.get(posts.size() - 1).getId() : null;

        List<PostSummaryResponse> postList = posts.stream()
                .map(PostSummaryResponse::fromEntity)
                .toList();

        return new PostListResponse(postList, nextCursor, hasNext);
    }

    /**
     * 게시글 상세조회
     */
    public PostDetailResponse getPostDetails(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POST));

        if (post.getDeleted()) {
            throw new CustomException(ErrorCode.NOT_FOUND_POST);
        }

        return new PostDetailResponse(
                post.getTitle(),
                post.getContent(),
                post.getNickname(),
                post.getCreatedAt()
        );
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(int postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POST));

        if (post.getDeleted()) {
            throw new CustomException(ErrorCode.NOT_FOUND_POST);
        }

        post.deletePost();
    }
}
