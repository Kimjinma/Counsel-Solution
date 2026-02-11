package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMP_NO")
public class CounselorEntity {

    @Id
    @Column(name = "EMP_NO", nullable = false, length = 10)
    private String empNo;

    @Column(name = "EMP_SE", nullable = false, length = 10)
    private String empSe;

    @Column(name = "EMP_NM", nullable = false, length = 100)
    private String empName;

    @Column(name = "DEPT_CD", nullable = false, length = 10)
    private String deptCode;

    @Column(name = "GNDR", nullable = false, length = 10)
    private String gender;

    @Column(name = "EMAIL", nullable = false, length = 320)
    private String email;

    @Column(name = "MBL_TELNO", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "ZIP", nullable = false, length = 5)
    private String zip;

    @Column(name = "ADDR", nullable = false, length = 200)
    private String address;

    @Column(name = "DADDR", nullable = false, length = 200)
    private String detailAddress;

    @Column(name = "USER_NO", nullable = false, length = 10)
    private String userNo;

    @Column(name = "USER_SE", nullable = false, length = 10)
    private String userSe;

    @Column(name = "LGN_ID", nullable = false, length = 100)
    private String loginId;

    @ManyToOne
    @JoinColumn(name = "USER_NO", referencedColumnName = "USER_NO", insertable = false, updatable = false)
    private UserEntity user;

    // Getters & Setters
}