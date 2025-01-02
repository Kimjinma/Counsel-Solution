package com.example.counsel.service;

import com.example.counsel.dto.ProgramApplyRequest;
import com.example.counsel.entity.Program;
import com.example.counsel.entity.ProgramRequest;
import com.example.counsel.repository.ProgramRepository;
import com.example.counsel.repository.ProgramRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramRequestRepository programRequestRepository;

    public ProgramService(ProgramRepository programRepository, ProgramRequestRepository programRequestRepository) {
        this.programRepository = programRepository;
        this.programRequestRepository = programRequestRepository;
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgram(Long programNo) {
        return programRepository.findById(programNo)
                .orElseThrow(() -> new RuntimeException("Program not found: " + programNo));
    }

    public boolean applyForProgram(ProgramApplyRequest request) {
        // 이미 신청한 프로그램인지 확인
        boolean exists = programRequestRepository.existsByStudentNoAndProgramNo(request.getStudentNo(), request.getProgramNo());
        if (exists) {
            return false; // 이미 신청된 경우
        }

        // ProgramApplyRequest -> ProgramRequest로 매핑
        ProgramRequest programRequest = new ProgramRequest();
        programRequest.setStudentNo(request.getStudentNo());
        programRequest.setProgramNo(request.getProgramNo());
        // 필요한 다른 필드도 매핑

        // 변환된 ProgramRequest 저장
        programRequestRepository.save(programRequest);
        return true; // 저장 성공
    }

    public List<ProgramRequest> getRequestsByStudentNo(String studentNo) {
        return programRequestRepository.findByStudentNo(studentNo);
    }
}
