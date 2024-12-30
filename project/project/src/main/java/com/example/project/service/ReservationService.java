/*
package com.example.project.service;

import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private RequestRepository requestRepository;

    // 예약 상태 업데이트 메서드
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
}*/
