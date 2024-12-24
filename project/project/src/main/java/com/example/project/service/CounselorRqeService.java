package com.example.project.service;

import com.example.project.dto.CounselRequestDTO;
import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
