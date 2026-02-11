package com.example.project.service;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.CounselorEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CounselorRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private UserRepository userRepository; // UserRepository 추가

    @Transactional
    public void updateCounselorInfo(String loginId, UpdateUserDTO updateUserDTO) {
        CounselorEntity counselorEntity = counselorRepository.findByLoginId(loginId);
        if (counselorEntity == null) {
            throw new IllegalArgumentException("Counselor not found with LGN_ID: " + loginId);
        }

        // UserEntity 가져오기
        UserEntity userEntity = counselorEntity.getUser();
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found for LGN_ID: " + loginId);
        }

        // CounselorEntity 필드 업데이트
        if (updateUserDTO.getEmail() != null) {
            counselorEntity.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getAddress() != null) {
            counselorEntity.setAddress(updateUserDTO.getAddress());
        }
        if (updateUserDTO.getPhoneNumber() != null) {
            counselorEntity.setMobileNumber(updateUserDTO.getPhoneNumber());
        }

        // UserEntity 비밀번호 업데이트
        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isEmpty()) {
            userEntity.setPassword(updateUserDTO.getPassword());
        }

        // 변경 사항 저장
        counselorRepository.save(counselorEntity); // CounselorEntity 저장
        userRepository.save(userEntity); // UserEntity 저장
    }
}
