package com.example.counsel.controller;

import com.example.counsel.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "reset-password"; // reset-password.mustache
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam String email, Model model) {
        boolean success = passwordResetService.resetPassword(email);
        if (success) {
            model.addAttribute("message", "임시 비밀번호가 이메일로 전송되었습니다.");
        } else {
            model.addAttribute("error", "해당 이메일로 등록된 사용자가 없습니다.");
        }
        return "reset-password";
    }
}
