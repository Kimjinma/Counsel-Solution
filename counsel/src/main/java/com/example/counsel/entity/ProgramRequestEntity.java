package com.example.counsel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reqNo; // 신청 번호 (Primary Key)

    private Long programNo; // 프로그램 번호
    private String studentNo; // 학생 번호
    private String approvalYn; // 승인 여부

    // Getter와 Setter

    public Long getReqNo() {
        return reqNo;
    }

    public void setReqNo(Long reqNo) {
        this.reqNo = reqNo;
    }

    public Long getProgramNo() {
        return programNo;
    }

    public void setProgramNo(Long programNo) { // 메서드 이름 확인
        this.programNo = programNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getApprovalYn() {
        return approvalYn;
    }

    public void setApprovalYn(String approvalYn) {
        this.approvalYn = approvalYn;
    }
}
