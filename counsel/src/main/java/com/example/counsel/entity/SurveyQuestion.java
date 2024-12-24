package com.example.counsel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BFHD_SRVY_QITEM")
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BFHD_SRVY_QITEM_NO")
    private Long questionItemId;

    @Column(name = "QITEM_TYPE_CD", nullable = false)
    private String questionTypeCode;

    @Column(name = "QITEM_CN", nullable = false)
    private String questionContent;
}
