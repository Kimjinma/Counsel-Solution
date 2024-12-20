package com.example.project.controller;

import com.example.project.entity.RequestEntity;

import com.example.project.service.RequestService;
import org.apache.coyote.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;


    @GetMapping("/student")
    public String getStudentRequset(@RequestParam("studentno") String studentno, Model model) {

        List<RequestEntity> Request = requestService.getCounselRequestsByStudentNo(studentno);

        model.addAttribute("counselRequests", Request);

        return "request"; // 업데이트 후 리다이렉트

    }
}

