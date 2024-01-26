package me.minkh.app.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyOAuth2UserService oAuth2UserService;

    private static final String[] AUTH_LIST = { "/info/**" };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 옵션 disable
        http.csrf(AbstractHttpConfigurer::disable);
        // AUTH_LIST의 경로 외 나머지 옵션은 인증이 필요 없음
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers(AUTH_LIST).authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/accounts").authenticated()
                        .anyRequest().permitAll());
        // OAuth2
        http.oauth2Login(o -> o.loginPage("/login").userInfoEndpoint(u -> u.userService(oAuth2UserService)));
        return http.build();
    }
}