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
    @PostMapping("/mymodalupdate")
    public ResponseEntity<String> submitSurvey(@RequestBody CsDTO csDTO) {
        try {
            // 서비스에서 설문 데이터 처리
            Integer counselorNo = Integer.valueOf(csDTO.getCounselorNo());

            csService.updateaprv(counselorNo, csDTO);
            return ResponseEntity.ok("설문조사가 완료되었습니다.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 에러 처리
        }
    }
}