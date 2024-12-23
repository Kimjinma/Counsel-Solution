package com.example.project.service;

import com.example.project.entity.CounselorEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CounselorRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselorManagementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CounselorRepository counselorRepository;

    /**
     * 학번 조회 메서드
     */
    public String getStudentNoByLoginId(String username) {
        // 1. 사용자 정보 조회
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("해당 로그인 아이디를 가진 사용자가 없습니다: " + username);
        }

        // 2. 사용자 번호로 상담사 정보 조회
        CounselorEntity counselor = counselorRepository.findByUser_UserNo(user.getUserNo());
        if (counselor == null) {
            throw new IllegalArgumentException("해당 사용자 번호를 가진 상담사가 없습니다: " + user.getUserNo());
        }

        // 3. 상담사 번호 반환
        return counselor.getEmpNo();
    }

}

