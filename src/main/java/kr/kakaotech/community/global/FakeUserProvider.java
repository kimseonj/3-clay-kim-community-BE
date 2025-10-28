package kr.kakaotech.community.global;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakeUserProvider {
    public UUID getCurrentUserId() {
        // 나중엔 JWT or SecurityContext에서 꺼내게 됨
        return UUID.fromString("ead3f04a-6585-4c0a-b376-75fa1ac2668f");
    }
}
