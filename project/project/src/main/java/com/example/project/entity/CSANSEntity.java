package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CS_ANS")
public class CSANSEntity {

    @Id
    @Column(name = "QITEM_ANS_NO", length = 10)
    private String id; // 답변항목번호
    @Column(name = "QITEM_NO", length = 3)
    private Integer qitem; // 점수

    @Column(name = "QITEM_NO", length = 10)
    private String questionId; // 문항번호

    @Column(name = "MULT_QITEM_ANS_CN", length = 4000)
    private String multipleChoiceAnswerContent; // 객관식 답변내용

    @Column(name = "SCR", length = 3)
    private Integer score; // 점수
    @Column(name = "SUB_ANS", length = 3)
    private Integer subans; // 점수

    @ManyToOne
    @JoinColumn(name = "STDNT_NO", referencedColumnName = "STDNT_NO", insertable = false, updatable = false)
    private StudentEntity student; // USER_INFO와 연결

    // Getters and Setters
}
