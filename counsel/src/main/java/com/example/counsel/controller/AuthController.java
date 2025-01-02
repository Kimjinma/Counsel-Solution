package com.example.counsel.controller;

import com.example.counsel.dto.JoinDTO;
import com.example.counsel.service.JoinService;
import com.example.counsel.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 1. AuthController - 인증 관련 통합
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final JoinService joinService;
    private final PasswordResetService passwordResetService;

    public AuthController(JoinService joinService, PasswordResetService passwordResetService) {
        this.joinService = joinService;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "redirect:/auth/login";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage() {
        return "reset-password";
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

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
