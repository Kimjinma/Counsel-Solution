package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "CNS_REQ_INFO")
@Getter
@Setter
@NoArgsConstructor
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQ_NO")
    private Long requestNumber; // 신청 번호


    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity empNo; // USER_INFO와 연결

    @ManyToOne
    @JoinColumn(name = "STDNT_NO", referencedColumnName = "STDNT_NO", insertable = false, updatable = false)
    private StudentEntity studentNo; // USER_INFO와 연결

    @Column(name = "SCHD_NO", nullable = false, length = 10)
    private String scheduleNumber; // 일정번호

    @Column(name = "CNS_PRGSS", nullable = false)
    private Integer counselingProgress; // 상담진행상태

    @Column(name = "CNS_NO", nullable = false, length = 10)
    private String counselingNumber; // 상담통합번호

    @Column(name = "SCHD_CONTS", length = 4000)
    private String scheduleContents; // 상담내용

    @Column(name = "APRV_YN", length = 1)
    private String approvalStatus; // 완료 여부 ('Y', 'N')

    @Column(name = "APLY_NMTM")
    private Integer applyCount; // 횟수

    @Column(name = "SCHD_ST_DT")
    private String scheduleStartDate; // 일정 시작일시

    @Column(name = "SCHD_END_DT")
    private String scheduleEndDate; // 일정 종료일시

    @Column(name = "SCHD_YN", length = 1)
    private String scheduleStatus; // 예약 여부 ('Y', 'N')


}
