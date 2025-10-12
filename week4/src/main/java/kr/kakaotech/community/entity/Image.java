package kr.kakaotech.community.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "images")
public class Image {
    @Id
//    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String url;
    LocalDateTime localDateTime;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
        this.localDateTime = LocalDateTime.now();
    }

    public void updateImage(String url) {
        this.url = url;
        this.localDateTime = LocalDateTime.now();
    }
}
