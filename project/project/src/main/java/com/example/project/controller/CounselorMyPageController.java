package com.example.project.controller;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/counselormypage")
public class CounselorMyPageController {

    @Autowired
    private CounselorService counselorService;

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
}