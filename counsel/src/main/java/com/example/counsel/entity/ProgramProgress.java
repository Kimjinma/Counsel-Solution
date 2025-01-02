package com.example.counsel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRGRM_PRGRS")
@Getter
@Setter
public class ProgramProgress {

    @Id
    @Column(name = "PRGRM_NO")
    private Long prgrmNo;

    @Column(name = "STDNT_NO")
    private String stdntNo;

    @Column(name = "APRV_YN")
    private String aprvYn;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "APPLY_YN")
    private String applyYn;

    @Column(name = "ENGAGE_YN")
    private String engageYn;
}