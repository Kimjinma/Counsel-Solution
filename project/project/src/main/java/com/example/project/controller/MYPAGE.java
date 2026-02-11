/*
package com.example.project.controller;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")

public class MYPAGE {

    @Autowired
    private UserService userService;

    // 마이페이지 조회 화면
    @GetMapping("/mypage/profile")
    public String myPageView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "mypage"; // mypage.html 반환
    }


    @PostMapping("/update")
    public String updateUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                 UpdateUserDTO updateUserDTO, Model model) {
        String username = userDetails.getUsername();
        userService.updateStudent(username, updateUserDTO);
        model.addAttribute("message", "User information updated successfully.");
        return "redirect:/mypage"; // 업데이트 후 리다이렉트
    }
}*/
