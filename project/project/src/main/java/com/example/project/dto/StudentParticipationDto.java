package com.example.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentParticipationDto {
    private String studentNo;
    private String name;
    private String approvalStatus;  // 승인 상태
    private int programNumber; // 프로그램 번호

}