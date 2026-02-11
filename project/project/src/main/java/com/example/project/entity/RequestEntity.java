package com.example.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "CNS_REQ_INFO") // 테이블 이름 매핑
public class RequestEntity {

    @Id
    @Column(name = "CNS_NO")
    private Integer cnsNo;

    @ManyToOne
    @JoinColumn(name = "STDNT_NO", referencedColumnName = "STDNT_NO", insertable = false, updatable = false)
    private StudentEntity studentNo;

    @Column(name = "CNS_TYPE")
    private String counselingType;

    @Column(name = "APRV_YN", length = 3)
    private String aprv; // 완료여부

    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity emp;

    @Column(name = "SCHED_NO") // 일정 번호
    private String schedNo;

    @Column(name = "CNS_PRGSS") // 진행 상태
    private Integer counselingProgress;

    @Column(name = "SCHD_ST_DT") // 일정 시작일
    private LocalDate scheduleStartDate;

    @Column(name = "SCHD_END_DATE") // 일정 종료일
    private LocalDate scheduleEndDate;

    @Column(name = "SCHD_YN") // 일정 여부
    private String scheduleYn;

    @Column(name = "CNS_REASON") // 상담 사유
    private String counselingReason;

    @Column(name = "SCHD_CONTS", length = 4000) // 상담 내용
    private String counselingContent;

    @Column(name = "APPLY_YN") // 신청 여부
    private String applyYn;

    @Column(name = "APPLY_NMTR") // 신청 횟수
    private Integer applyCount;

    @Column(name="CS_YN", length = 3)
    private String csyn;
    // 기본 생성자 추가
    public RequestEntity() {
    }

    public String getCS_YN() {
        return csyn;
    }
    public void setCS_YN(String csyn) {
        this.csyn=csyn;

        }
    // Getter 및 Setter
    public Integer getCnsNo() {
        return cnsNo;
    }

    public void setCnsNo(Integer cnsNo) {
        this.cnsNo = cnsNo;
    }
    // Getter
    public String getAprv() {
        return aprv;
    }

    // Setter
    public void setAprv(String aprv) {
        this.aprv = aprv;
    }

    public StudentEntity getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(StudentEntity studentNo) {
        this.studentNo = studentNo;
    }

    public String getCounselingType() {
        return counselingType;
    }

    public void setCounselingType(String counselingType) {
        this.counselingType = counselingType;
    }// Getters and Setters
    public String getCsyn() {
        return csyn;
    }
    public void setCsyn(String csyn) {
        this.csyn = csyn;
    }



    public CounselorEntity getEmp() {
        return emp;
    }

    public void setEmp(CounselorEntity emp) {
        this.emp = emp;
    }


    public String getSchedNo() {
        return schedNo;
    }

    public void setSchedNo(String schedNo) {
        this.schedNo = schedNo;
    }

    public Integer getCounselingProgress() {
        return counselingProgress;
    }

    public void setCounselingProgress(Integer counselingProgress) {
        this.counselingProgress = counselingProgress;
    }

    public LocalDate getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(LocalDate scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public LocalDate getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(LocalDate scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleYn() {
        return scheduleYn;
    }

    public void setScheduleYn(String scheduleYn) {
        this.scheduleYn = scheduleYn;
    }

    public String getCounselingReason() {
        return counselingReason;
    }

    public void setCounselingReason(String counselingReason) {
        this.counselingReason = counselingReason;
    }

    public String getCounselingContent() {
        return counselingContent;
    }

    public void setCounselingContent(String counselingContent) {
        this.counselingContent = counselingContent;
    }

    public String getApplyYn() {
        return applyYn;
    }

    public void setApplyYn(String applyYn) {
        this.applyYn = applyYn;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;

    }

}
