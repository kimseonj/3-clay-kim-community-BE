package kr.kakaotech.community.repository;

import kr.kakaotech.community.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
