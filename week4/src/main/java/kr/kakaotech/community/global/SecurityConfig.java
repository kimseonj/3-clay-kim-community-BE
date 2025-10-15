package kr.kakaotech.community.global;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CORS, CSRF, formLogin 설정
        http
                .csrf(auth -> {
                    auth.disable();
                    log.info("CSRF enabled");
                })
                .formLogin(auth -> auth.disable())
                .httpBasic(auth -> auth.disable())
                // session 설정을 stateless 로 변경
                .sessionManagement((session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)));

        // 경로별 인가
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/*", "/posts")
                        .permitAll());

        http
                .addFilterAt(
                        new LoginFilter(authenticationConfiguration.getAuthenticationManager()),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}
