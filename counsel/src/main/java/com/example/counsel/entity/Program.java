package com.example.counsel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRGRM")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRGRM_NO", nullable = false)
    private Long programNo;

    @Column(name = "EMP_NO", length = 10, nullable = false)
    private String employeeNo;

    @Column(name = "FILE_NO", nullable = false)
    private Integer fileNo;

    @Column(name = "PRGRM_NM", length = 100, nullable = false)
    private String programName;

    @Column(name = "PLAN_NOPE", nullable = false)
    private Integer planNope;

    @Column(name = "DSCSN_REG_YMD", nullable = false)
    private LocalDateTime decisionRegistrationDate;

    @Column(name = "EDU_PLC_NM", length = 100, nullable = false)
    private String educationPlaceName;

    @Column(name = "DDLN_CRTR_YMD", nullable = false)
    private LocalDateTime deadlineCriteriaDate;
}
