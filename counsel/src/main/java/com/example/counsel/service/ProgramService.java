package com.example.counsel.service;

import com.example.counsel.dto.ProgramRequest;
import com.example.counsel.entity.ProgramRequestEntity;
import com.example.counsel.repository.ProgramRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRequestRepository programRequestRepository;

    public ProgramService(ProgramRequestRepository programRequestRepository) {
        this.programRequestRepository = programRequestRepository;
    }

    public boolean applyProgram(ProgramRequest request) {
        try {
            // ProgramRequestEntity 변환 및 저장
            ProgramRequestEntity entity = new ProgramRequestEntity();
            entity.setProgramNo(request.getProgramNo()); // 메서드 이름 수정
            entity.setStudentNo(request.getStudentNo());
            entity.setApprovalYn("N"); // 기본 값 설정

            programRequestRepository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<ProgramRequestEntity> getRequestsByStudentNo(String studentNo) {
        return programRequestRepository.findByStudentNo(studentNo);
    }

}

