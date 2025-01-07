package com.example.project.controller;

import com.example.project.dto.StudentParticipationDto;
import com.example.project.service.ProgramRqeService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/counselor/mypage")
public class ProgramReqController {

    private final ProgramRqeService programRqeService;

    public ProgramReqController(ProgramRqeService programRqeService) {
        this.programRqeService = programRqeService;
    }

    /**
     * 특정 프로그램의 학생 참여 정보 조회
     */
    @GetMapping("/students")
    public String getStudents(@RequestParam("programNo") int programNo, Model model) {
        List<StudentParticipationDto> studentList = programRqeService.getStudentListByProgramNo(programNo);
        System.out.println("Student List: " + studentList);

        model.addAttribute("studentList", studentList);
        return "counselormypage :: #participantList";
    }

    @PostMapping("/approveStudent")
    public String approveStudent(
            @RequestParam("studentNo") String studentNo,
            @RequestParam("programNo") int programNo,
            RedirectAttributes redirectAttributes) {
        System.out.println("Received studentNo: " + studentNo);
        System.out.println("Received programNo: " + programNo);

        // 승인 로직 처리
        boolean isApproved = programRqeService.approveStudent(studentNo, programNo);
        if (isApproved) {
            redirectAttributes.addFlashAttribute("message", "승인되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "이미 승인된 상태입니다.");
        }

        return "redirect:/counselor/mypage/list";
    }

    @GetMapping("/participants")
    public String getParticipants(
            @RequestParam("programNo") int programNo,
            Model model) {
        // 승인된 학생 리스트 조회
        List<StudentParticipationDto> participantList = programRqeService.getApprovedParticipants(programNo);

        // 뷰에 전달할 데이터 설정
        model.addAttribute("participantList", participantList);

        return "counselormypage :: #participantTable"; // 특정 HTML 조각 반환 (Thymeleaf Fragment)
    }



    @PostMapping("/cancelStudent")
    public String cancelStudent(
            @RequestParam("studentNo") String studentNo,
            @RequestParam("programNo") int programNo,
            RedirectAttributes redirectAttributes) {
        boolean isCanceled = programRqeService.cancelStudent(studentNo, programNo);
        if (isCanceled) {
            redirectAttributes.addFlashAttribute("message", "취소가 완료되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "이미 취소된 상태입니다.");
        }

        return "redirect:/counselor/mypage/list";
    }

}