package com.example.counsel.service;

import com.example.counsel.entity.ProgramProgress;
import com.example.counsel.entity.ProgramRequest;
import com.example.counsel.repository.ProgramProgressRepository;
import com.example.counsel.repository.ProgramRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProgramService {

    @Autowired
    private final ProgramRequestRepository programRequestRepository;
    public ProgramService(ProgramRequestRepository programRequestRepository) {
        this.programRequestRepository = programRequestRepository;
    }

    public List<ProgramRequest> getRequestsByStudentNo(String studentNo) {
        return programRequestRepository.findByStudentNo(studentNo);
    }


    private ProgramProgressRepository progressRepository;

    public boolean applyProgram(ProgramRequest request) {

        Long programNo = request.getProgramNo();
        String studentNo = request.getStudentNo();

        try {
            // 이미 신청한 프로그램인지 확인
            if (progressRepository.existsByPrgrmNoAndStdntNo(
                    request.getProgramNo(), request.getStudentNo())) {
                return false;
            }

            // 신청 정보 저장
            ProgramProgress progress = new ProgramProgress();
            progress.setPrgrmNo(request.getProgramNo());
            progress.setStdntNo(request.getStudentNo());
            progress.setAprvYn("Y");        // 승인 여부
            progress.setApplyYn("Y");       // 신청 여부
            progress.setEngageYn("N");      // 참여 여부 (초기값 N)

            progressRepository.save(progress);
            return true;
        } catch (Exception e) {
            // 로깅 추가 필요
            System.err.println("Program application failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}

