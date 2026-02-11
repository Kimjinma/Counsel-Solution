package com.example.project.service;

import com.example.project.dto.JoinDTO;
import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    public void joinProcess(JoinDTO joinDTO) {
        // 기존 사용자 체크
        if (userRepository.existsByUserNo(joinDTO.getUsername())) {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo("U" + System.currentTimeMillis());
        userEntity.setUserSe("USER"); // 기본 사용자 권한
        userEntity.setUsername(joinDTO.getUsername());
        userEntity.setPassword(joinDTO.getPassword()); // 비밀번호를 평문으로 저장
        userEntity.setPasswordChangeDate(LocalDateTime.now());
        userEntity.setPasswordErrorCount(0);
        userEntity.setLastLoginDate(LocalDateTime.now());

        userRepository.save(userEntity);

    }

}
