package com.example.project.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRGRM")
@Getter
@Setter

public class ProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRGRM_NO")
    private int programNumber; // 프로그램 번호

    @Column(name = "PRGRM_NM", nullable = false, length = 100)
    private String programName; // 프로그램명

    @Column(name = "PLAN_NOPE", nullable = false)
    private int maxParticipants; // 참여가능 인원

    @Column(name = "DDLN_CRTR_YMD")
    private LocalDateTime startDate; // 프로그램 날짜

    @Column(name = "EDU_PLC_NM", length = 100)
    private String venue; // 진행 장소

    @Column(name = "FILE_NO")
    private int fileNumber; // 파일 번호

    @Column(name = "DDLN_CRTR_YMD")
    private int ddln; // 마감일
    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO")
    private CounselorEntity enpNo; //

    // Getters, Setters, and Constructors
}


