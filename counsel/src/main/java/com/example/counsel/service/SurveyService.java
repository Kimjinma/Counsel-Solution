package com.example.counsel.service;

import com.example.counsel.dto.SurveyResultRequest;
import com.example.counsel.entity.SurveyResult;
import com.example.counsel.repository.SurveyResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyResultRepository surveyResultRepository;

    public SurveyService(SurveyResultRepository surveyResultRepository) {
        this.surveyResultRepository = surveyResultRepository;
    }

    @Transactional
    public void saveSurveyResults(List<SurveyResultRequest> surveyResults) {
        List<SurveyResult> entities = surveyResults.stream().map(request -> {
            SurveyResult entity = new SurveyResult();
            entity.setQuestionItemId(request.getQuestionItemId());
            entity.setQuestionTypeCode(request.getQuestionTypeCode());
            entity.setAnswerId(request.getAnswerId());
            return entity;
        }).collect(Collectors.toList());

        surveyResultRepository.saveAll(entities);
    }
}