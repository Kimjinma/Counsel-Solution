package com.example.counsel.dto;

public class SurveyAnswer {
    private String questionItemNo;
    private String questionTypeCode;
    private String subjectiveAnswer; // 주관식 답변
    private String answerNo; // 객관식 답변 번호

    // Getters and Setters
    public String getQuestionItemNo() {
        return questionItemNo;
    }

    public void setQuestionItemNo(String questionItemNo) {
        this.questionItemNo = questionItemNo;
    }

    public String getQuestionTypeCode() {
        return questionTypeCode;
    }

    public void setQuestionTypeCode(String questionTypeCode) {
        this.questionTypeCode = questionTypeCode;
    }

    public String getSubjectiveAnswer() {
        return subjectiveAnswer;
    }

    public void setSubjectiveAnswer(String subjectiveAnswer) {
        this.subjectiveAnswer = subjectiveAnswer;
    }

    public String getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(String answerNo) {
        this.answerNo = answerNo;
    }
}
