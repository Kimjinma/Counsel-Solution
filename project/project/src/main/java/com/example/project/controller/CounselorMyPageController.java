package com.example.project.controller;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.service.CounselorService;
import com.example.project.service.StudentRequestService;
import com.example.project.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/counselormypage")
public class CounselorMyPageController {

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private StudentManagementService testService;

    @Autowired
    private StudentRequestService requestService;

    @GetMapping
    public String myPageView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "counselormypage"; // counselormypage.html 반환
    }

    @PostMapping("/update-info")
    public String updateCounselorInfo(@AuthenticationPrincipal UserDetails userDetails,
                                      UpdateUserDTO updateUserDTO, Model model) {
        System.out.println("Request to update counselor info: " + updateUserDTO);
        try {
            counselorService.updateCounselorInfo(userDetails.getUsername(), updateUserDTO);
            model.addAttribute("message", "Counselor information updated successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "mypage";
    }
    @GetMapping("/testcounselRequests")
    public String counselRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String studentNo = testService.getStudentNoByLoginId(username);
        List<RequestEntity> counselRequests = requestService.getRequestsByStudentNo(studentNo);
        model.addAttribute("testcounselRequest", counselRequests);
        System.out.println(counselRequests); // 데이터 확인
        return "mypage";
    }
}

