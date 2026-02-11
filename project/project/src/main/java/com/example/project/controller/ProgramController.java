/*
package com.example.project.controller;

import com.example.project.dto.ProgramProDTO;
import com.example.project.service.ProgramRqeService;
import com.example.project.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")


public class ProgramController {

    @Autowired
    private ProgramRqeService programRqeService;

    @Autowired
    private StudentManagementService studentManagementService;

    @GetMapping("/program")

    public String getStudents(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String studentNo = studentManagementService.getStudentNoByLoginId(username);
        List<ProgramProDTO> programList = programRqeService.getRequestsByStudentNo(studentNo);

        model.addAttribute("programList", programList);

        return "mypage/programList";
    }
}

*/
