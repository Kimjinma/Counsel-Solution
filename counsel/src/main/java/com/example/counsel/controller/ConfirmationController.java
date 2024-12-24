package com.example.counsel.controller;

import com.example.counsel.entity.ProgramRequestEntity;
import com.example.counsel.service.ProgramService; // Service 클래스 임포트
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfirmationController { // 클래스 선언 추가

    private final ProgramService programService; // 의존성 선언

    // 생성자 주입
    public ConfirmationController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/confirmation")
    public String confirmationPage(Model model) {
        // 사용자 식별 정보를 가져와야 합니다. 아래는 예시로 "학생번호"를 사용
        String studentNo = "학생번호"; // 실제 사용자 정보를 세션이나 SecurityContext에서 가져올 것
        List<ProgramRequestEntity> requests = programService.getRequestsByStudentNo(studentNo);
        model.addAttribute("requests", requests);
        return "confirmation"; // confirmation.mustache 파일을 반환
    }
}
