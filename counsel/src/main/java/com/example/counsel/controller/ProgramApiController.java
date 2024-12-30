package com.example.counsel.controller;

import com.example.counsel.dto.ProgramRequest;
import com.example.counsel.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/program")
public class ProgramApiController {

    private final ProgramService programService;

    public ProgramApiController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/apply")
    public ResponseEntity<Map<String, Object>> applyProgram(@RequestBody ProgramRequest request) {
        boolean isApplied = programService.applyProgram(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", isApplied);

        return ResponseEntity.ok(response);
    }
}
