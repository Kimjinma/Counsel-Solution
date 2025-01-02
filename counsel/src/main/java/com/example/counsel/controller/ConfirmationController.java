package com.example.counsel.controller;

import com.example.counsel.entity.ProgramProgress;
import com.example.counsel.service.ProgramService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfirmationController {

    private final ProgramService programService;

    public ConfirmationController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/confirmation")
    public String confirmationPage(Model model) {
        // SecurityContext에서 로그인된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentNo = authentication.getName(); // 사용자 ID로 학번을 추출했다고 가정

        // 신청 내역 가져오기
        List<ProgramProgress> requests = programService.getRequestsByStdntNo(studentNo);
        model.addAttribute("requests", requests);
        return "confirmation"; // confirmation.mustache 파일 반환
    }
}
