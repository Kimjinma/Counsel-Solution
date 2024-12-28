package com.example.project.service;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounselorRqeService {
    @Autowired
    private RequestRepository requestRepository;

    public List<CounselRequestDTO> getRequestsByEmpNo(String empNo) {
        List<RequestEntity> entities = requestRepository.findByEmp_EmpNo(empNo);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CounselRequestDTO convertToDTO(RequestEntity entity) {
        CounselRequestDTO dto = new CounselRequestDTO();
        dto.setCnsNo(entity.getCnsNo());
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
    public boolean updateReservationStatus(Integer cnsNo, String applyYn, String scheduleYn) {
        // 예약 엔티티 조회
        RequestEntity reservation = requestRepository.findById(cnsNo).orElse(null);

        if (reservation != null) {
            // 상태 및 일정 업데이트
            reservation.setApplyYn(applyYn); // "승인" 또는 "대기중"
            reservation.setScheduleYn(scheduleYn); // 일정 시작일 업데이트
            requestRepository.save(reservation); // 변경 사항 저장
            return true;
        }

        return false; // 예약 엔티티가 없을 경우 실패 처리
    }
}
