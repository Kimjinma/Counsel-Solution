package com.example.project.service;

import com.example.project.dto.CsDTO;
import com.example.project.entity.CsEntity;
import com.example.project.entity.RequestEntity;
import com.example.project.repository.CsRepository;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
public class CsService {

    @Autowired
    private CsRepository csRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Transactional
    public void saveSurvey(Integer cnsNo, CsDTO csDTO) {
        // 1. 상담 신청 정보 가져오기
        RequestEntity requestEntity = requestRepository.findByCnsNo(cnsNo);
        if (requestEntity == null) {
            throw new IllegalArgumentException("상담 정보를 찾을 수 없습니다.");
        }

        // 2. 상담 완료 여부 확인
        if (!"Y".equals(requestEntity.getAprv())) {
            throw new IllegalStateException("상담이 완료되지 않았으므로 만족도 조사를 진행할 수 없습니다.");
        }

        // 3. 상담사 번호 추출
        String empNo = requestEntity.getEmp() != null ? requestEntity.getEmp().getEmpNo() : null;
        if (empNo == null) {
            throw new IllegalArgumentException("상담사 번호가 유효하지 않습니다.");
        }

        // 4. 중복 조사 확인
        if ("Y".equals(requestEntity.getCS_YN())) {
            throw new IllegalStateException("이미 해당 상담에 대해 만족도 조사를 완료하셨습니다.");
        }
        System.out.println("CS_YN check passed");
        // 5. 점수 합산 및 저장
        int totalScore = 0;
        totalScore += csDTO.getQuestion1() != null ? csDTO.getQuestion1() : 0;
        totalScore += csDTO.getQuestion2() != null ? csDTO.getQuestion2() : 0;
        totalScore += csDTO.getQuestion3() != null ? csDTO.getQuestion3() : 0;

        CsEntity csEntity = new CsEntity();
        csEntity.setId(UUID.randomUUID().toString()); // 고유 ID 생성
        csEntity.setScore(totalScore); // 합산 점수 저장
        csEntity.setSubans(csDTO.getQuestion4()); // 주관식 답변 저장

        csEntity.setSubans(csDTO.getQuestion5()); // 주관식 답변 저장
        csEntity.setSubans(csDTO.getQuestion6()); // 주관식 답변 저장

        csEntity.setCnsno(requestEntity); // 상담 정보 연결
        csEntity.setEmpNo(requestEntity.getEmp());
        // 6. 저장
        csRepository.save(csEntity);
        requestEntity.setCS_YN("Y");
        requestRepository.save(requestEntity);
        System.out.println("RequestEntity updated with CS_YN = 'Y'");
    }
    public boolean isValidCnsNo(Integer cnsNo) {
        RequestEntity requestEntity = requestRepository.findByCnsNo(cnsNo);
        return requestEntity != null; // null 여부로 존재 확인
    }

}
