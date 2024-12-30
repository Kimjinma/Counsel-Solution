package com.example.counsel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BFHD_SRVY_ANS")
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QITEM_ANS_NO")
    private Long answerId;

    @Column(name = "BFHD_SRVY_QITEM_NO", nullable = false)
    private Long questionItemId;

    @Column(name = "QITEM_TYPE_CD", nullable = false)
    private String questionTypeCode;

    @Column(name = "SUB_QITEM_ANS_CN")
    private String subjectiveAnswer;

    @Column(name = "MUT_QITEM_ANS_CN")
    private String multipleChoiceAnswer;
}
