package com.example.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class ProgramProDTO {



    private int programNumber; // 프로그램 번호
    private LocalDateTime registrationDate; // 프로그램 날짜
    private int progressCount; // 프로그램 진행 횟수
    private String programStatus; // 프로그램 상태
    private String approvalStatus; // 승인 상태
    private int rating; // 프로그램 평가 점수
    private String applyYn; // 신청 여부
    private String engageYn; // 참여 여부
    private String student; // 학번
    private String programName; // 프로그램 이름
    private int plannope; //프로그램 참여가능인원
    private String eduplcnm; //프로그램 진행장소

}

