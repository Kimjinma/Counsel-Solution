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
    @Column(name = "CNS_NO")
    private Long requestNumber; // 상담번호

    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity empNo; // 직원과 연결 (USER_INFO와 연결)

    @ManyToOne
    @JoinColumn(name = "STDNT_NO", referencedColumnName = "STDNT_NO", insertable = false, updatable = false)
    private StudentEntity studentNo; // 학생과 연결

    @Column(name = "SCHED_NO", length = 10)
    private String scheduleNumber; // 일정번호

    @Column(name = "CNS_PRGSS")
    private Integer counselingProgress; // 상담진행상태

    @Column(name = "SCHD_START_DATE")
    private String scheduleStartDate; // 일정 시작일

    @Column(name = "SCHD_END_DATE")
    private String scheduleEndDate; // 일정 종료일

    @Column(name = "SCHD_YN", length = 1)
    private String scheduleStatus; // 일정 여부 ('Y', 'N')

    @Column(name = "CNS_TYPE", length = 10)
    private String counselingType; // 상담 유형

    @Column(name = "CNS_REASON", length = 10)
    private String counselingReason; // 상담 사유

    @Column(name = "CNS_CONTENT", length = 4000)
    private String counselingContent; // 상담 내용

    @Column(name = "APPLY_YN", length = 1)
    private String applyStatus; // 신청 여부 ('Y', 'N')

    @Column(name = "APPLY_NMTR")
    private Integer applyCount; // 신청 횟수
}
