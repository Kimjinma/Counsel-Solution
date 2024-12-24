package com.example.counsel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/program")
public class ProgramController {

    @GetMapping("/group")
    public String programGroup() {
        return "program-group"; // program-group.mustache 렌더링
    }

    @GetMapping("/peer")
    public String programPeer() {
        return "program-peer"; // program-peer.mustache 렌더링
    }
}
