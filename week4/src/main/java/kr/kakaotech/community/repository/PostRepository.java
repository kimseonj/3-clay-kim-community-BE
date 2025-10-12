package kr.kakaotech.community.repository;

import kr.kakaotech.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM posts p " +
            "ORDER BY p.id DESC " +
            "LIMIT :size")
    List<Post> findTopPost(@Param("size") int size);

    @Query("SELECT p FROM posts p " +
            "WHERE p.id < :cursor " +
            "ORDER BY p.id DESC " +
            "LIMIT :size")
    List<Post> findPostByCursor(@Param("cursor") int cursor, @Param("size") int size);
}
