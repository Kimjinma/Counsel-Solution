package com.example.counsel.service;

import com.example.counsel.dto.AdviceRequest;
import com.example.counsel.entity.AdviceEntity;
import com.example.counsel.repository.AdviceRepository;
import org.springframework.stereotype.Service;

@Service
public class AdviceService {

    private final AdviceRepository adviceRepository;

    public AdviceService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public boolean processAdvice(AdviceRequest request) {
        try {
            // 생성자 호출 시 매개변수와 순서 확인
            AdviceEntity adviceEntity = new AdviceEntity(
                    null, // ID는 자동 생성
                    request.getQuestion1(),
                    request.getQuestion2(),
                    request.getQuestion3(),
                    request.getSchedule()
            );
            adviceRepository.save(adviceEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

