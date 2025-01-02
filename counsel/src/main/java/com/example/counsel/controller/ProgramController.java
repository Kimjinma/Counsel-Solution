package com.example.counsel.controller;

import com.example.counsel.dto.ProgramApplyRequest;
import com.example.counsel.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    // 프로그램 리스트 페이지
    @GetMapping("/list")
    public String listPrograms(Model model) {
        model.addAttribute("programs", programService.getAllPrograms());
        return "program-group"; // program-group.mustache
    }

    // 프로그램 상세 페이지
    @GetMapping("/details/{programNo}")
    public String getProgramDetails(@PathVariable Long programNo, Model model) {
        model.addAttribute("program", programService.getProgram(programNo));
        return "program-group-plus"; // program-group-plus.mustache
    }

    // 프로그램 신청 처리
    @PostMapping("/apply")
    public ResponseEntity<?> applyForProgram(@RequestBody ProgramApplyRequest request) {
        programService.applyForProgram(request);
        return ResponseEntity.ok("프로그램 신청이 완료되었습니다.");
    }
}
