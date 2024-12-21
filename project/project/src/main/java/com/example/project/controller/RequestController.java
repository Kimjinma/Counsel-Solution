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

public class RequestController {

    @Autowired
    private testService testService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/mypage/counselRequests")
    public String getCounselRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        System.out.println("Logged-in username: " + username);
        System.out.println("Logged-in Username: " + userDetails.getUsername());

        // 학번 조회
        String studentNo = testService.getStudentNoByLoginId(username);
        if (studentNo == null) {
            model.addAttribute("error", "학번 정보를 찾을 수 없습니다.");
            System.out.println("Student No: " + studentNo);

            return "mypage";

        }

        // 상담 신청 정보 조회
        List<RequestEntity> counselRequests = requestService.getRequestsByStudentNo(studentNo);
        model.addAttribute("counselRequests", counselRequests); // 데이터 추가
        System.out.println("Counsel Requests: " + counselRequests);

        return "mypage"; // HTML 파일 이름

    }

    }
