package com.example.counsel.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER_INFO")
public class User_Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO", length = 10, nullable = false)
    private String userNo; // 사용자 번호

    @Column(name = "LGN_ID", length = 100, nullable = false)
    private String loginId; // 로그인 아이디

    @Column(name = "PSWD", length = 512, nullable = false)
    private String password; // 비밀번호

    @Column(name = "PSWD_CHG_DT")
    private LocalDateTime passwordChangeDate; // 비밀번호 변경 일시

    @Column(name = "PSWD_ERR_NMTN", length = 2)
    private Integer passwordErrorCount; // 비밀번호 오류 횟수

    @Column(name = "LAST_LGN_DT")
    private LocalDateTime lastLoginDate; // 최종 로그인 일시

    @Column(name = "SOCIAL_ID", length = 100)
    private String socialId; // 소셜 아이디

    @Column(name = "ACCESS_TOKEN", length = 100)
    private String accessToken; // 액세스 토큰

    @Column(name = "REFRESH_TOKEN", length = 100)
    private String refreshToken; // 리프레시 토큰

    @Column(name = "USER_SE", length = 10)
    private String userType; // 사용자 구분
}
