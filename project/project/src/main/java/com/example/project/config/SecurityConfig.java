package com.example.project.config;

import com.example.project.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final LoginService customUserDetailsService;

    public SecurityConfig(LoginService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll() // 누구나 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 권한만 접근 가능

                        // 학생 마이페이지 접근 권한 설정
                        .requestMatchers("/mypage/profile").hasAnyRole("ADMIN", "USER", "STUDENT") // 학생 개인정보 수정 접근 권한
                        .requestMatchers("/mypage/counselRequests").hasAnyRole("ADMIN", "USER", "STUDENT") // 학생 상담 신청 정보 접근 권한

                        // 상담사 마이페이지 접근 권한 설정
                        .requestMatchers("/counselor/mypage/profile").hasAnyRole("ADMIN", "USER", "COUNSELOR") // 상담사 개인정보 수정 접근 권한
                        .requestMatchers("/counselor/mypage/counselRequests").hasAnyRole("ADMIN", "USER", "COUNSELOR") // 상담사 상담 신청 정보 접근 권한
                )

                .formLogin(auth -> auth
                        .loginPage("/login") // 로그인 페이지 URL
                        .loginProcessingUrl("/loginProc") // 로그인 처리 URL
                        .defaultSuccessUrl("/mypage/profile", true) // 로그인 성공 후 이동할 기본 URL
                        .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 평문 비밀번호 사용 (테스트용, 보안 취약)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
