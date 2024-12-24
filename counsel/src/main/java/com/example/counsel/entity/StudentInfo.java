package com.example.counsel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STDNT_INFO") // 테이블 이름 매핑
public class StudentInfo {

    @Id
    @Column(name = "STDNT_NO", nullable = false, length = 10)
    private String studentNo; // 학번

    @Column(name = "STDNT_NM", nullable = true, length = 100)
    private String studentName; // 학생 이름

    @Column(name = "GNDR", nullable = true, length = 10)
    private String gender; // 성별

    @Column(name = "EMAIL", nullable = true, length = 320)
    private String email; // 이메일

    @Column(name = "MBL_TELNO", nullable = true, length = 15)
    private String mobilePhoneNumber; // 휴대전화 번호

    @Column(name = "ZIP", nullable = true, length = 5)
    private String zipCode; // 우편번호

    @Column(name = "ADDR", nullable = true, length = 200)
    private String address; // 주소

    @Column(name = "DADDR", nullable = true, length = 200)
    private String detailAddress; // 상세 주소

    @Column(name = "USER_NO", nullable = false, length = 10)
    private String userNo; // 사용자 번호
}
