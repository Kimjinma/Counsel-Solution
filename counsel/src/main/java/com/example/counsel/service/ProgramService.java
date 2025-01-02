package com.example.counsel.service;

import com.example.counsel.dto.ProgramApplyRequest;
import com.example.counsel.entity.Program;
import com.example.counsel.entity.ProgramProgress;
import com.example.counsel.repository.ProgramProgressRepository;
import com.example.counsel.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramProgressRepository programProgressRepository;

    public ProgramService(ProgramRepository programRepository, ProgramProgressRepository programProgressRepository) {
        this.programRepository = programRepository;
        this.programProgressRepository = programProgressRepository;
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
        if (programProgressRepository.existsByStdntNoAndPrgrmNo(request.getStudentNo(), request.getProgramNo())) {
            return false; // 이미 신청된 경우
        }

        // 신청 데이터를 저장
        ProgramProgress programProgress = new ProgramProgress();
        programProgress.setStudentNo(request.getStudentNo());
        programProgress.setProgramNo(request.getProgramNo());
        programProgressRepository.save(programProgress);
        return true; // 저장 성공
    }

    public List<ProgramProgress> getRequestsByStdntNo(String stdntNo) {
        return programProgressRepository.findByStdntNo(stdntNo);
    }
}
