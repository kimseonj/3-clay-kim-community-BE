package kr.kakaotech.community.repository;

import kr.kakaotech.community.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostStatusRepository extends JpaRepository<PostStatus, Integer> {
}
