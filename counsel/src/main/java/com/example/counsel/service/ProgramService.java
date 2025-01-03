package com.example.counsel.service;

import com.example.counsel.dto.ProgramApplyRequest;
import com.example.counsel.entity.Program;
<<<<<<< HEAD
import com.example.counsel.entity.ProgramProgress;
import com.example.counsel.repository.ProgramProgressRepository;
import com.example.counsel.repository.ProgramRepository;
=======
import com.example.counsel.entity.ProgramRequest;
import com.example.counsel.repository.ProgramRepository;
import com.example.counsel.repository.ProgramRequestRepository;
>>>>>>> d690af495b77a4f3cf383869fa9cb431ca2e7b13
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
<<<<<<< HEAD
    private final ProgramProgressRepository programProgressRepository;

    public ProgramService(ProgramRepository programRepository, ProgramProgressRepository programProgressRepository) {
        this.programRepository = programRepository;
        this.programProgressRepository = programProgressRepository;
=======
    private final ProgramRequestRepository programRequestRepository;

    public ProgramService(ProgramRepository programRepository, ProgramRequestRepository programRequestRepository) {
        this.programRepository = programRepository;
        this.programRequestRepository = programRequestRepository;
>>>>>>> d690af495b77a4f3cf383869fa9cb431ca2e7b13
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
<<<<<<< HEAD
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
=======
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
>>>>>>> d690af495b77a4f3cf383869fa9cb431ca2e7b13
}
