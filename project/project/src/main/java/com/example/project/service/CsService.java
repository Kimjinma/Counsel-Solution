package com.example.project.service;

import com.example.project.dto.CsDTO;
import com.example.project.entity.CsEntity;
import com.example.project.entity.RequestEntity;
import com.example.project.repository.CsRepository;
import com.example.project.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
public class CsService {

    @Autowired
    private CsRepository csRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Transactional
    public void updateaprv(Integer cnsNo, CsDTO csDTO) {
        // 1. 상담 신청 정보 가져오기
        RequestEntity requestEntity = requestRepository.findByCnsNo(cnsNo);
        if (requestEntity == null) {
            throw new IllegalArgumentException("상담 신청 정보를 찾을 수 없습니다.");
        }

        // 2. 완료 여부(aprv) 확인
        if ("N".equals(requestEntity.getAprv())) {
            throw new IllegalStateException("만족도 조사가 불가능합니다. 상담이 완료되지 않았습니다.");
        }

        // 3. 만족도 조사 여부(csyn) 확인
        if ("Y".equals(requestEntity.getCsyn())) {
            throw new IllegalStateException("이미 만족도 조사가 완료되었습니다.");
        }

        // 4. 객관식 점수와 서브 항목 점수 합산
        int totalScore = 0;
        totalScore += csDTO.getQuestion1() != null ? csDTO.getQuestion1() : 0;  // 질문 1 점수
        totalScore += csDTO.getQuestion2() != null ? csDTO.getQuestion2() : 0;  // 질문 2 점수
        totalScore += csDTO.getQuestion3() != null ? csDTO.getQuestion3() : 0;  // 질문 3 점수
        totalScore += csDTO.getSubAns() != null ? csDTO.getSubAns() : 0;  // 서브 항목 점수

        // 5. CS_ANS 테이블에 만족도 조사 정보 저장
        CsEntity csEntity = new CsEntity();
        csEntity.setId("someId"); // 실제로 설정할 값
        csEntity.setScore(totalScore); // 합산된 점수 저장
        csEntity.setQuestionId(csDTO.getQuestionId()); // 문항 ID
        csEntity.setMultipleChoiceAnswerContent(csDTO.getMultipleChoiceAnswerContent()); // 주관식 답변 내용

        // 6. CS_ANS 테이블에 저장
        csRepository.save(csEntity);

        // 7. 상담 신청 정보 테이블에서 만족도 조사 완료 상태로 업데이트
        requestEntity.setCsyn("Y"); // 만족도 조사 완료 상태로 변경
        requestRepository.save(requestEntity); // 상담 신청 정보 업데이트

    }
}
