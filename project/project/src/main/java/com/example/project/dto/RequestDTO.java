package com.example.project.dto;

import java.time.LocalDate;

public class RequestDTO {

    private Integer cnsNo; // 상담 번호
    private String studentNo; // 학생 번호 (ID 값만 전달)
    private String counselingType; // 상담 유형
    private String aprv; // 완료여부
    private String empNo; // 상담사 번호 (ID 값만 전달)
    private String schedNo; // 일정 번호
    private Integer counselingProgress; // 진행 상태
    private LocalDate scheduleStartDate; // 일정 시작일
    private LocalDate scheduleEndDate; // 일정 종료일
    private String scheduleYn; // 일정 여부
    private String counselingReason; // 상담 사유
    private String counselingContent; // 상담 내용
    private String applyYn; // 신청 여부
    private Integer applyCount; // 신청 횟수
    private String csyn; // 만족도 조사 여부

    // 기본 생성자
    public RequestDTO() {
    }

    // 매개변수를 받는 생성자
    public RequestDTO(Integer cnsNo, String studentNo, String counselingType, String aprv, String empNo, String schedNo,
                      Integer counselingProgress, LocalDate scheduleStartDate, LocalDate scheduleEndDate, String scheduleYn,
                      String counselingReason, String counselingContent, String applyYn, Integer applyCount, String csyn) {
        this.cnsNo = cnsNo;
        this.studentNo = studentNo;
        this.counselingType = counselingType;
        this.aprv = aprv;
        this.empNo = empNo;
        this.schedNo = schedNo;
        this.counselingProgress = counselingProgress;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.scheduleYn = scheduleYn;
        this.counselingReason = counselingReason;
        this.counselingContent = counselingContent;
        this.applyYn = applyYn;
        this.applyCount = applyCount;
        this.csyn = csyn;
    }

    // Getters and Setters
    public Integer getCnsNo() {
        return cnsNo;
    }

    public void setCnsNo(Integer cnsNo) {
        this.cnsNo = cnsNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getCounselingType() {
        return counselingType;
    }

    public void setCounselingType(String counselingType) {
        this.counselingType = counselingType;
    }

    public String getAprv() {
        return aprv;
    }

    public void setAprv(String aprv) {
        this.aprv = aprv;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
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

    public String getCsyn() {
        return csyn;
    }

    public void setCsyn(String csyn) {
        this.csyn = csyn;
    }
}
