package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "PRGRM")
public class ProgramEntity {

    @Id
    @Column(name = "PRGRM_NM", nullable = false, length = 100)
    private String programName;


    @Column(name = "FILE_NO")
    private Integer fileNo;

    @Column(name = "PLAN_NOPE")
    private Integer planCapacity;

    @Column(name = "DSCN_REG_YMD")
    private LocalDateTime decisionDate;

    @Column(name = "EDU_PLC_NM", length = 100)
    private String educationPlace;

    @Column(name = "DDLN_CRTR_YMD")
    private LocalDateTime deadlineDate;
    @ManyToOne
    @JoinColumn(name = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity emp;
    // Getters and Setters
}


