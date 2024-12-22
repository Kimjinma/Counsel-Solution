package com.example.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_INFO")
public class UserEntity {

    @Id
    @Column(name = "USER_NO", nullable = false, length = 10)
    private String userNo;

    @Column(name = "USER_SE", nullable = false, length = 10)
    private String userSe;

    @Column(name = "lgn_id", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "PSWD", nullable = false, length = 255)
    private String password;

    @Column(name = "PSWD_CHG_DT")
    private LocalDateTime passwordChangeDate;

    @Column(name = "PSWD_ERR_NMTN", nullable = false)
    private int passwordErrorCount = 0;

    @Column(name = "LAST_LGN_DT")
    private LocalDateTime lastLoginDate;

    @Column(name = "SOCIAL_ID", length = 50)
    private String socialId;

    @Column(name = "ACCESS_TOKEN", length = 500)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 500)
    private String refreshToken;

    // 기본 생성자
    public UserEntity() {}

    // Getters 및 Setters
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserSe() {

        return userSe;
    }

    public void setUserSe(String userSe) {
        this.userSe = userSe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(LocalDateTime passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    public int getPasswordErrorCount() {
        return passwordErrorCount;
    }

    public void setPasswordErrorCount(int passwordErrorCount) {
        this.passwordErrorCount = passwordErrorCount;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
