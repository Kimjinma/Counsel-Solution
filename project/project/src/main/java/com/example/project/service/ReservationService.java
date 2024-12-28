package com.example.project.service;

import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private RequestRepository reservationRepository;

    // 예약 상태 업데이트 메서드
    public boolean updateReservationStatus(Integer cnsNo, String applyYn, String scheduleYn) {
        // 예약 엔티티 조회
        // Optional 값에서 직접 꺼내기
        RequestEntity reservation = reservationRepository.findById(cnsNo).orElse(null);

        if (reservation != null) {
            // 엔티티가 존재하는 경우 처리
            reservation.setApplyYn(applyYn);
            reservation.setScheduleYn(scheduleYn);
            reservationRepository.save(reservation);
            return true;
        } else {
            // 엔티티가 존재하지 않는 경우
            return false;
        }
    }
}
