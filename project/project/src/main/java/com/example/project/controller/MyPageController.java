package com.example.project.controller;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.dto.StudentRequestDTO;
import com.example.project.dto.UpdateUserDTO;
import com.example.project.service.StudentRequestService;
import com.example.project.service.UserService;
import com.example.project.service.StudentManagementService;
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
    private StudentManagementService studentManagementService;

    @Autowired
    private StudentRequestService requestService;

    @GetMapping("/profile")
    public String myPageView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "mypage";
    }

    @PostMapping("/update")
    public String updateUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                                 UpdateUserDTO updateUserDTO, Model model) {
        String username = userDetails.getUsername();
        userService.updateStudent(username, updateUserDTO);
        model.addAttribute("message", "User information updated successfully.");
        return "redirect:/mypage/profile";
    }

    @GetMapping("/counselRequests")
    public String getStudents(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String studentNo = studentManagementService.getStudentNoByLoginId(username);
        List<StudentRequestDTO> counselRequests  = requestService.getRequestsByStudentNo(studentNo);
        model.addAttribute("counselRequests", counselRequests);
        return "mypage";
    }
}