package com.example.counsel.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProgramDTO {
    private Long prgrmNo;        // 프로그램 번호
    private String prgrmNm;        // 프로그램명
    private String planNope;       // 참여가능인원
    private LocalDateTime prgrmBgngYmd;  // 프로그램 시작일자
    private String eduPlcNm;       // 진행장소
    private LocalDateTime ddlnCntrYmd;   // 마감일
    private String empNo;          // 교직원번호
    private String fileNo;         // 파일번호
}
