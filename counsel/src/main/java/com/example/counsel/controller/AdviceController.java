package com.example.counsel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @GetMapping("/person")
    public String advicePersonPage() {
        return "advice-person";
    }
}


