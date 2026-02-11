/*
package com.example.project.controller;

import com.example.project.entity.RequestEntity;
import com.example.project.service.RequestService;
import com.example.project.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private testService testService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/mypage/counselRequests")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<RequestEntity> counselRequests = requestService.getRequestsByStudentNo(username);
        model.addAttribute("counselRequests", counselRequests);
        return "mypage";
    }
}*/
