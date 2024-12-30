package com.example.project.service;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.entity.CounselorEntity;
import com.example.project.entity.RequestEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CounselorRepository;
import com.example.project.repository.RequestRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CounselorRqeService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CounselorRepository counselorRepository;

    @Transactional

    public List<CounselRequestDTO> getRequestsByEmpNo(String empNo) {
        List<RequestEntity> entities = requestRepository.findByEmp_EmpNo(empNo);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CounselRequestDTO convertToDTO(RequestEntity entity) {
        CounselRequestDTO dto = new CounselRequestDTO();
        dto.setCnsNo(entity.getCnsNo());

        // 디버깅 로그 추가
        System.out.println("Mapped cnsNo: " + entity.getCnsNo());

        dto.setStudentNo(entity.getStudentNo() != null ? entity.getStudentNo().getStudentNo() : null);
        dto.setEmpNo(entity.getEmp() != null ? entity.getEmp().getEmpNo() : null);
        dto.setSchedNo(entity.getSchedNo());
        dto.setCounselingProgress(entity.getCounselingProgress());
        dto.setScheduleStartDate(entity.getScheduleStartDate() != null ? entity.getScheduleStartDate().toString() : null);
        dto.setScheduleEndDate(entity.getScheduleEndDate() != null ? entity.getScheduleEndDate().toString() : null);
        dto.setScheduleYn(entity.getScheduleYn());
        dto.setCounselingType(entity.getCounselingType());
        dto.setCounselingReason(entity.getCounselingReason());
        dto.setCounselingContent(entity.getCounselingContent());
        dto.setApplyYn(entity.getApplyYn());

        dto.setApplyCount(entity.getApplyCount());
        return dto;
    }

    public boolean updateReservationStatus(String username, Integer cnsNo, String applyYn, LocalDate scheduleStartDate) {
        // 1. 사용자 엔티티 조회
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        // 2. 사용자 번호(userNo)를 통해 직원 번호(empNo) 조회
        CounselorEntity empNo = counselorRepository.findByEmpNo(userEntity.getUserNo());
        if (empNo == null) {
            throw new IllegalArgumentException("Employee not found for userNo: " + userEntity.getUserNo());
        }

        // 3. 직원 번호(empNo)를 통해 상담 요청 조회
        RequestEntity reservation = requestRepository.findByEmp_EmpNoAndCnsNo(empNo.getEmpNo(), cnsNo);
        if (reservation != null) {
            // 상태 및 일정 업데이트
            reservation.setApplyYn(applyYn); // "승인" 또는 "대기중"
            reservation.setScheduleStartDate(scheduleStartDate); // 일정 시작일 업데이트
            requestRepository.save(reservation); // 변경 사항 저장
            return true;
        }

        return false; // 상담 요청을 찾을 수 없을 경우 실패 처리
    }
    @Transactional

    public CounselRequestDTO updateschdconts(String username, Integer cnsNo, String counselingContent) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        // 사용자 번호(userNo)를 통해 직원 번호(empNo) 조회
        CounselorEntity empNo = counselorRepository.findByEmpNo(userEntity.getUserNo());
        if (empNo == null) {
            throw new IllegalArgumentException("Employee not found for userNo: " + userEntity.getUserNo());
        }

        // 직원 번호(empNo)를 통해 상담 요청 조회
        RequestEntity reservation = requestRepository.findByEmp_EmpNoAndCnsNo(empNo.getEmpNo(), cnsNo);
        if (reservation != null) {
            // 상담 내용 업데이트
            reservation.setCounselingContent(counselingContent);
            requestRepository.save(reservation);

            // DTO로 변환하여 반환
            return convertToDTO(reservation);
        }

        throw new IllegalArgumentException("No reservation found for empNo: " + empNo.getEmpNo() + " and cnsNo: " + cnsNo);
    }
    public List<Map<String, String>> getCounselContentByUsername(String username) {
        // 사용자 엔티티 조회
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        // 직원 번호(empNo) 조회
        CounselorEntity empNo = counselorRepository.findByEmpNo(userEntity.getUserNo());
        if (empNo == null) {
            throw new IllegalArgumentException("Employee not found for userNo: " + userEntity.getUserNo());
        }

        // 직원 번호로 상담 요청(cnsNo) 리스트 조회
        List<RequestEntity> requestList = requestRepository.findByEmp_EmpNo(empNo.getEmpNo());
        if (requestList.isEmpty()) {
            throw new IllegalArgumentException("No requests found for empNo: " + empNo.getEmpNo());
        }

        // 각 cnsNo에 해당하는 상담 기록 필드를 가져옴
        return requestList.stream().map(request -> {
            Map<String, String> map = new HashMap<>();
            map.put("cnsNo", String.valueOf(request.getCnsNo())); // cnsNo
            map.put("content", request.getCounselingContent());   // 상담 기록 필드
            return map;
        }).collect(Collectors.toList());
    }

}