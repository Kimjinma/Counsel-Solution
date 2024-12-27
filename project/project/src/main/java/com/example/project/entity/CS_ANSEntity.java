package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CS_ANS")
public class CS_ANSEntity {


    @Id
    @Column(name = "QITEM_ANS_NO", length = 10, nullable = false)
    private String questionAnswerNumber; // 문항답변번호

    @Column(name = "MUT_QITEM_ANS_CN", columnDefinition = "VARCHAR2(4000)")
    private String multipleChoiceAnswerContent; // 객관식답변내용

    @Column(name = "SCR", nullable = false)
    private Integer score; // 점수

    @Column(name = "SUB_ANS", columnDefinition = "VARCHAR2(4000)")
    private String subjectiveAnswer; // 주관식답변

    @ManyToOne
    @JoinColumn(name = "STDTN_NO", referencedColumnName = "STDTN_NO")
    private StudentEntity student; // 학번 (외래키)

    @Column(name = "QITEM_SIGN_NO", length = 10)
    private String displaySymbolNumber; // 표시문항번호

    @Column(name = "QITEM_NO", length = 10)
    private String questionNumber; // 문항번호

    @Column(name = "QITEM_TYPE_CD", columnDefinition = "VARCHAR2(100)")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType; // 객관식/주관식 문항유형코드
    // Getters and Setters
    public enum QuestionType {
        MULTIPLE_CHOICE("multiple_choice"),
        SHORT_ANSWER("short_answer"),
        LONG_ANSWER("long_answer");

        private final String code;

        QuestionType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}

