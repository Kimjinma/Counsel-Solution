package com.example.project.dto;

public class StudentRequestDTO {

    private Integer cnsNo; // 상담 번호
    private String studentNo; // 학생 번호
    private String empNo; // 직원 번호
    private String schedNo; // 일정 번호
    private Integer counselingProgress; // 진행 상태
    private String scheduleStartDate; // 일정 시작일
    private String scheduleEndDate; // 일정 종료일
    private String scheduleYn; // 일정 여부
    private String counselingType; // 상담 유형
    private String counselingReason; // 상담 사유
    private String counselingContent; // 상담 내용
    private String applyYn; // 신청 여부
    private Integer applyCount; // 신청 횟수

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

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(String scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleYn() {
        return scheduleYn;
    }

    public void setScheduleYn(String scheduleYn) {
        this.scheduleYn = scheduleYn;
    }

    public String getCounselingType() {
        return counselingType;
    }

    public void setCounselingType(String counselingType) {
        this.counselingType = counselingType;
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
