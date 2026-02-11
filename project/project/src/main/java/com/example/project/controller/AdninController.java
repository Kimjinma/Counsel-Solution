package com.example.project.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AdninController {
    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }
}
