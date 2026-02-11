package com.example.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CSDTO {

    private String questionAnswerNumber; // 문항답변번호

    private String multipleChoiceAnswerContent; // 객관식답변내용

    private Integer score; // 점수

    private String subjectiveAnswer; // 주관식답변

    private String studentNumber; // 학번 (외래키로 연결된 학번)

    private String displaySymbolNumber; // 표시문항번호

    private String questionNumber; // 문항번호

    private String questionType; // 객관식/주관식 문항유형코드
}
