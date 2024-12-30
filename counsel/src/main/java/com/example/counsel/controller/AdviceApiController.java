package com.example.counsel.controller;

import com.example.counsel.dto.AdviceRequest;
import com.example.counsel.service.AdviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/advice")
public class AdviceApiController {

    private final AdviceService adviceService;

    public AdviceApiController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @PostMapping("/submit")
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
}

