package com.example.project.controller;

import com.example.project.entity.RequestEntity;
import com.example.project.service.RequestService;
import com.example.project.service.UserService;
import com.example.project.service.testService;
import com.example.project.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/mypage/counselRequests")
public class RequestController {

    @Autowired
    private testService testService;

    @Autowired
    private RequestService requestService;

    @GetMapping
    public String counselRequestsView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        // 학번 조회
        String studentNo = testService.getStudentNoByLoginId(username);

        // 상담 신청 정보 조회
        List<RequestEntity> counselRequests = requestService.getRequestsByStudentNo(studentNo);

        // 모델에 데이터 추가
        model.addAttribute("username", username);
        model.addAttribute("counselRequests", counselRequests);

        return "mypage"; // mypage.html 반환
    }
}
