package com.example.project.controller;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.service.CounselorManagementService;
import com.example.project.service.CounselorRqeService;
import com.example.project.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/counselor/mypage")
public class CounselorMyPageController {

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private CounselorManagementService counselorManagementService;
    @Autowired
    private CounselorRqeService requestService;

    // 상담사 마이페이지 조회 화면
    @GetMapping("/profile")
    public String myPageView(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "counselormypage"; // counselor_mypage.html 반환
    }

    // 상담사 개인정보 수정
    @PostMapping("/update")
    public String updateCounselorInfo(@AuthenticationPrincipal UserDetails userDetails,
                                      UpdateUserDTO updateUserDTO, Model model) {
        String username = userDetails.getUsername();
        try {
            counselorService.updateCounselorInfo(username, updateUserDTO);
            model.addAttribute("message", "Counselor information updated successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/counselor/mypage/profile"; // 업데이트 후 리다이렉트
    }

    // 상담 신청 정보 조회
    @GetMapping("/counselRequests")
    public String counselRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String counselorNo = counselorManagementService.getStudentNoByLoginId(username);
        List<CounselRequestDTO> counselRequests = requestService.getRequestsByEmpNo(counselorNo);
        model.addAttribute("counselRequests", counselRequests);
        return "counselormypage";
    }

    // 예약 상태 업데이트
    @PostMapping("/updateStatus")
    public String updateReservationStatus(
            @AuthenticationPrincipal UserDetails userDetails, // 로그인된 사용자 정보
            @RequestParam Integer cnsNo, // 상담 번호
            @RequestParam String applyYn, // 상태 ("승인" 또는 "대기중")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduleStartDate, // 일정 시작일
            Model model) {

        // 1. 로그인한 사용자 이름 확인
        String username = userDetails.getUsername();
        System.out.println("Received data: username=" + username + ", cnsNo=" + cnsNo + ", applyYn=" + applyYn + ", scheduleStartDate=" + scheduleStartDate);

        try {
            // 2. 예약 상태 업데이트 호출
            boolean isUpdated = requestService.updateReservationStatus(username, cnsNo, applyYn, scheduleStartDate);

            if (isUpdated) {
                model.addAttribute("message", "예약 상태가 성공적으로 업데이트되었습니다.");
            } else {
                model.addAttribute("error", "예약 상태 업데이트에 실패했습니다.");
            }
        } catch (IllegalArgumentException e) {
            // 3. 오류 처리
            model.addAttribute("error", "오류 발생: " + e.getMessage());
            return "errorPage"; // 에러 페이지로 리다이렉트 (필요 시 수정)
        }

        // 4. 상담 신청 정보 페이지로 리다이렉트
        return "redirect:/counselor/mypage/counselRequests";
    }
}
