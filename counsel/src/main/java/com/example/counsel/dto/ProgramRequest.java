package com.example.counsel.dto;

public class ProgramRequest {
    private Long programNo;
    private String studentNo;

    // Getter와 Setter
    public Long getProgramNo() {
        return programNo;
    }

    public void setProgramNo(Long programNo) {
        this.programNo = programNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
