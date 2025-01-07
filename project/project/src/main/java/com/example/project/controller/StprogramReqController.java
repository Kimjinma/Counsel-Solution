package com.example.project.controller;

import com.example.project.dto.ProgramProDTO;
import com.example.project.service.ProgramRqeService;
/*
import com.example.project.service.ProgramService;
*/
import com.example.project.service.StProgramRqeService;
import com.example.project.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")

public class StprogramReqController {

    @Autowired
    private StudentManagementService studentManagementService;
/*
    private final ProgramService programService;
*/

    private final StProgramRqeService stprogramRqeService;

    public StprogramReqController(StProgramRqeService stprogramRqeService) {
        this.stprogramRqeService = stprogramRqeService;


    }

    @GetMapping("/studentlist")
    public String getStudents(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String StudentNo = studentManagementService.getStudentNoByLoginId(username);

        System.out.println("Fetched StudentNo: " + StudentNo);

        List<ProgramProDTO> programList = stprogramRqeService.getRequestsByStudentNo(StudentNo);

        System.out.println("programList size: " + programList.size());
        programList.forEach(program -> System.out.println("Program: " + program));

        model.addAttribute("programList", programList);

        return "mypage";
    }
   /* @GetMapping("/program/details/{programName}")
    public String getProgramDetails(@PathVariable("programName") String programName, Model model) {
        // 서비스 호출을 통해 프로그램 상세 정보를 가져옴
        ProgramProDTO programDetails = programService.getProgramDetailsByProgramName(programName);

        // 모델에 데이터 추가
        model.addAttribute("program", programDetails);

        // 프로그램 상세 정보를 표시할 HTML 파일 이름 반환
        return "program-group-plus";
    }*/

}

