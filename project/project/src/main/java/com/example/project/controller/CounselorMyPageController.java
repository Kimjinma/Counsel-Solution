package com.example.project.controller;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.dto.ProgramProDTO;
import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.service.CounselorManagementService;
import com.example.project.service.CounselorRqeService;
import com.example.project.service.CounselorService;
import com.example.project.service.ProgramRqeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/counselor/mypage")
public class CounselorMyPageController {

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private CounselorManagementService counselorManagementService;
    @Autowired
    private CounselorRqeService requestService;
    @Autowired
    private ProgramRqeService programRqeService;
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

    @PostMapping("/updateContent")
    public ResponseEntity<CounselRequestDTO> updateCounselingContent(
            @AuthenticationPrincipal UserDetails userDetails, // 로그인된 사용자 정보

            @RequestParam Integer cnsNo,
            @RequestParam String counselingContent) {

        String username = userDetails.getUsername();

        try {
            // 업데이트 및 결과 조회
            CounselRequestDTO updatedData = requestService.updateschdconts(username, cnsNo, counselingContent);
            return ResponseEntity.ok(updatedData);
        } catch (IllegalArgumentException e) {
            // 예외 처리
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getContent")
    public ResponseEntity<List<Map<String, String>>> contentRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "cnsNo", required = false) Integer cnsNo) {
        // 로그인된 사용자의 username 가져오기
        String username = userDetails.getUsername();

        try {
            // username으로 cnsNo를 찾고, cnsNo로 상담 기록을 가져오기
            List<Map<String, String>> records = requestService.getCounselContentByUsername(username);

            // 특정 cnsNo로 필터링
            if (cnsNo != null) {
                records = records.stream()
                        .filter(record -> cnsNo.equals(Integer.valueOf(record.get("cnsNo"))))
                        .collect(Collectors.toList());
            }

            // 데이터가 없으면 빈 리스트 반환
            if (records.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            // 데이터 반환
            return ResponseEntity.ok(records);

        } catch (IllegalArgumentException e) {
            // 오류 처리: 잘못된 요청
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/list")

    public String getStudents(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        String empNo = counselorManagementService.getStudentNoByLoginId(username);
        List<ProgramProDTO> programList = programRqeService.getRequestsByEmpNo(empNo);

        model.addAttribute("programList", programList);

        return "counselormypage";
    }
}
