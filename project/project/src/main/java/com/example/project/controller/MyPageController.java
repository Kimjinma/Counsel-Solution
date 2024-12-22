package com.example.project.controller;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.service.RequestService;
import com.example.project.service.UserService;
import com.example.project.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private testService testService;

    @Autowired
    private RequestService requestService;

    // 마이페이지 조회 화면
    @GetMapping("/profile")
    public String myPageView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "mypage"; // mypage.html 반환
    }

    // 개인정보 수정
    @PostMapping("/update")
    public String updateUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                 UpdateUserDTO updateUserDTO, Model model) {
        String username = userDetails.getUsername();
        userService.updateStudent(username, updateUserDTO);
        model.addAttribute("message", "User information updated successfully.");
        return "redirect:/mypage/profile"; // 업데이트 후 리다이렉트
    }

    // 상담 신청 정보
    @GetMapping("/counselRequests")
    public String counselRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String studentNo = testService.getStudentNoByLoginId(username);
        List<RequestEntity> counselRequests = requestService.getRequestsByStudentNo(studentNo);
        model.addAttribute("counselRequests", counselRequests);
        System.out.println(counselRequests); // 데이터 확인
        return "mypage";
    }
}
