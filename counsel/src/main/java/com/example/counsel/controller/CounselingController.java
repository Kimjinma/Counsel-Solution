package com.example.counsel.controller;

import com.example.counsel.dto.AdviceRequest;
import com.example.counsel.entity.ProgramRequest;
import com.example.counsel.service.AdviceService;
import com.example.counsel.service.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

// 2. CounselingController - 상담/프로그램 관련 통합
@Controller
@RequestMapping("/counseling")
public class CounselingController {

    private final AdviceService adviceService;
    private final ProgramService programService;

    public CounselingController(AdviceService adviceService, ProgramService programService) {
        this.adviceService = adviceService;
        this.programService = programService;
    }

    // 페이지 매핑
    @GetMapping("/advice/person")
    public String advicePersonPage() {
        return "advice-person";
    }

    @GetMapping("/program/group")
    public String programGroupPage() {
        return "program-group";
    }

    @GetMapping("/program/peer")
    public String programPeerPage() {
        return "program-peer";
    }

    // API 엔드포인트
    @PostMapping("/api/advice/submit")
    public ResponseEntity<Map<String, String>> submitAdvice(@RequestBody AdviceRequest request) {
        boolean isSubmitted = adviceService.processAdvice(request);
        Map<String, String> response = new HashMap<>();

        if (isSubmitted) {
            response.put("status", "success");
            response.put("message", "신청 완료");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "신청 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 프로그램 신청 처리
    @PostMapping("/api/program/apply")
    public ResponseEntity<Map<String, Object>> applyProgram(@RequestBody ProgramRequest request) {
        Map<String, Object> response = new HashMap<>();
        boolean isApplied = programService.applyProgram(request);

        if (isApplied) {
            response.put("status", "success");
            response.put("message", "프로그램 신청이 완료되었습니다.");
        } else {
            response.put("status", "error");
            response.put("message", "이미 신청한 프로그램이거나 신청 처리 중 오류가 발생했습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}