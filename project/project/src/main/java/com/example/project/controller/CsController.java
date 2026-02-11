package com.example.project.controller;

import com.example.project.dto.CsDTO;
import com.example.project.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage/counselorRequest/mymodal")  // 설문 관련 API 경로
public class CsController {

    @Autowired
    private CsService csService;

    // 설문조사 제출 API
    @PostMapping("/mymodalupdate/{cnsNo}")
    public ResponseEntity<String> submitSurvey(
            @PathVariable Integer cnsNo,
            @RequestBody CsDTO csDTO) {
        if (!csService.isValidCnsNo(cnsNo)) {
            return ResponseEntity.badRequest().body("유효하지 않은 상담 번호입니다.");
        }

        try {
            csService.saveSurvey(cnsNo, csDTO);
            return ResponseEntity.ok("만족도 조사가 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("이미 제출한 만족도 조사입니다");
        }
    }
}
