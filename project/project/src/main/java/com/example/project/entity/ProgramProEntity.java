package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "PRGRM_PRGRS")

public class ProgramProEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "PRGRM_NO", referencedColumnName = "PRGRM_NO")
    private ProgramEntity program; // 프로그램번호 (외래 키)



    @Column(name = "APRV_YN", length = 1)
    private char approvalStatus; // 승인 상태

    @Column(name = "RATING")
    private int rating; // 프로그램 평가 점수

    @Column(name = "APPLY_YN")
    private String applyYn; // 신청여부

    @Column(name = "ENGAGE_YN")
    private int engageYn; // 참여여부

    @ManyToOne
    @JoinColumn(name = "STDNT_NO", referencedColumnName = "STDNT_NO")
    private StudentEntity student; // 학번 (외래 키)

    // Getters, Setters, and Constructors
}


