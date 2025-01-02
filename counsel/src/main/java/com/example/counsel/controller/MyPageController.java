package com.example.counsel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    // 기본 마이페이지
    @GetMapping
    public String myPage(Model model) {
        // 필요한 데이터가 있다면 Model에 추가
        return "mypage";
    }

    // 상담 신청 현황 페이지
    @GetMapping("/mypage_st2")
    public String counselingStatus(Model model) {
        // 상담 현황 데이터를 Model에 추가할 수 있음
        return "mypage_st2";
    }

    // 프로그램 현황 페이지
    @GetMapping("/mypage_st3")
    public String programStatus(Model model) {
        // 프로그램 현황 데이터를 Model에 추가할 수 있음
        return "mypage_st3";
    }
}
