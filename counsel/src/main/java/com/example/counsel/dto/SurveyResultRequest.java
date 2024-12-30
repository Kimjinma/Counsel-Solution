package com.example.counsel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyResultRequest {
    private Long questionItemId;
    private String questionTypeCode;
    private Long answerId;
}