package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/co3")
    public String mainP() {
        return "mypage_co3"; // main.html을 반환
    }
}
