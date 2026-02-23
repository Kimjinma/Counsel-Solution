package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsDTO {
    // Score-related questions
    private Integer question1;
    private Integer question2;
    private Integer question3;

    // Subjective answers
    private String question4;
    private String question5;
    private String question6;
}
