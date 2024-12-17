package com.example.counsel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 사용자의 권한 정보 가져오기
        String role = null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty()) {
            role = authorities.iterator().next().getAuthority();
        }

        // 모델에 사용자 정보 추가
        model.addAttribute("id", username);  // 사용자의 ID (username)
        model.addAttribute("role", role);    // 사용자 권한 (role)
        model.addAttribute("username", username);  // 사용자명 (username)

        // main 페이지 반환
        return "main";
    }
}