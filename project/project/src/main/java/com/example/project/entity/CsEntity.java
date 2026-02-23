package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "CS_ANS")
public class CsEntity {

    @Id
    @Column(name = "QITEM_ANS_NO", length = 36)
    private String id; // 답변항목번호

    @Column(name = "QITEM_NO", length = 10)
    private String questionId; // 문항번호

    @Column(name = "MULT_QITEM_ANS_CN", length = 4000)
    private String multipleChoiceAnswerContent; // 객관식 답변내용

    @Column(name = "SCR", length = 3)
    private Integer score; // 점수

    @Column(name = "SUB_ANS_ONE", length = 1000)
    private String subAnsOne; // 주관식 답변

    @Column(name = "SUB_ANS_TWO", length = 1000)
    private String subAnsTwo; // 주관식 답변

    @Column(name = "SUB_ANS_THREE", length = 1000)
    private String subAnsThree; // 주관식 답변

    @Column(name = "QITEM_TYPE_CD", length = 3)
    private String type; // 문항 유형

    @ManyToOne
    @JoinColumn(name = "CNS_NO", referencedColumnName = "CNS_NO")
    private RequestEntity cnsno; // 상담 정보 연결

    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO")
    private CounselorEntity empNo; // 상담사 정보 연결

    // 기본 생성자
    public CsEntity() {
    }

    public CsEntity(String id, String questionId, String multipleChoiceAnswerContent, Integer score,
            String subAnsOne, String subAnsTwo, String subAnsThree, String type,
            RequestEntity cnsno, CounselorEntity empNo) {
        this.id = id;
        this.questionId = questionId;
        this.multipleChoiceAnswerContent = multipleChoiceAnswerContent;
        this.score = score;
        this.subAnsOne = subAnsOne;
        this.subAnsTwo = subAnsTwo;
        this.subAnsThree = subAnsThree;
        this.type = type;
        this.cnsno = cnsno;
        this.empNo = empNo;
    }
}
