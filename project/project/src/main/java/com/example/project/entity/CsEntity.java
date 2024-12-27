package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "CS_QITEM")
public class CsEntity {

    @Id
    @Column(name = "QITEM_ANS_NO", length = 10)
    private String id; // 답변항목번호


    @Column(name = "QITEM_NO", length = 10)
    private String questionId; // 문항번호

    @Column(name = "MULT_QITEM_ANS_CN", length = 4000)
    private String multipleChoiceAnswerContent; // 객관식 답변내용

    @Column(name = "SCR", length = 3)
    private Integer score; // 점수


    @Column(name = "SUB_ANS", length = 3)
    private String subans; // 점수

    @Column(name = "QITEM_TYPE_CD", length = 3)
    private String type;

    @ManyToOne
    @JoinColumn(name = "CNS_NO", referencedColumnName = "CNS_NO", insertable = false, updatable = false)
    private RequestEntity cnsno; //

    @ManyToOne
    @JoinColumn(name = "EMP_NO", referencedColumnName = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity emp; //

    // 기본 생성자 추가
    public CsEntity() {
        // 기본 생성자에는 아무것도 하지 않아도 됩니다.
    }

    public CsEntity(String id, String questionId, String multipleChoiceAnswerContent,
                        Integer score,String subans, String type, RequestEntity cnsno, CounselorEntity emp) {
        this.id = id;
        this.questionId = questionId;
        this.multipleChoiceAnswerContent = multipleChoiceAnswerContent;
        this.score = score;
        this.type = type;
        this.cnsno = cnsno;
        this.emp = emp;
        this.subans=subans;
    }
}

