package com.example.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsDTO {


    private Integer question1; // 객관식 질문 1 점수 (1~4)
    private Integer question2; // 객관식 질문 2 점수 (1~4)
    private Integer question3; // 객관식 질문 3 점수 (1~4)
    private String question4;  // 주관식 질문 4 내용
    private String question5;
    private String question6;

    private String subAns; // subAns 필드
    private String questionId; // 필드 선언
    private String multipleChoiceAnswerContent; // 필드 선언
    private String empname;


}