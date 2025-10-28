package kr.kakaotech.community.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt.expirationtime")
@Component
@Getter
@Setter
public class JwtExpirationProperties {
    private Long accessTime;
    private Long refreshTime;
}
