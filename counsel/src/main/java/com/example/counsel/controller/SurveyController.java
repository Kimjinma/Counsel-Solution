package com.example.counsel.controller;

import com.example.counsel.dto.ApiResponse;
import com.example.counsel.dto.SurveyResultRequest;
import com.example.counsel.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<Void>> submitSurvey(@RequestBody List<SurveyResultRequest> surveyResults) {
        try {
            surveyService.saveSurveyResults(surveyResults);
            return ResponseEntity.ok(new ApiResponse<>("설문조사가 성공적으로 저장되었습니다!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("저장 실패: " + e.getMessage()));
        }
    }

    @Controller
    @RequestMapping("/survey")
    public static class SurveyPageController {

        @GetMapping
        public String surveyPage() {
            return "survey";
        }
    }
}