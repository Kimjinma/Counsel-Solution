/*
package com.example.project.controller;

import com.example.project.entity.RequestEntity;
import com.example.project.service.RequestService;

import com.example.project.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @GetMapping
    public String getStudentRequests(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            throw new RuntimeException("UserDetails is null. Authentication failed.");
        }
        String studentNo = userDetails.getStudentNo(); // 로그인한 사용자의 studentNo 가져오기
        List<RequestEntity> requests = requestService.getRequestByStudentNo(studentNo); // 서비스 호출
        model.addAttribute("requests", requests); // 모델에 데이터 추가
        return "requests/list"; // 뷰 반환
    }


}
*/
