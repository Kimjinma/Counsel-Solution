package com.example.counsel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BFHD_SRVY_RST")
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BFHD_SRVY_QITEM_NO")
    private Long questionItemId;

    @Column(name = "QITEM_TYPE_CD")
    private String questionTypeCode;

    @Column(name = "QITEM_ANS_NO")
    private Long answerId;

    // Getters and Setters
}

