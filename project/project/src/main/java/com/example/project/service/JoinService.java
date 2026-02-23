package com.example.project.service;

import com.example.project.dto.JoinDTO;
import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        userEntity.setUserNo("U_" + UUID.randomUUID().toString().substring(0, 8)); // UUID를 활용하여 안전한 고유번호 생성
        userEntity.setUserSe("USER"); // 기본 사용자 권한
        userEntity.setUsername(joinDTO.getUsername());

        // 비밀번호 암호화 (BCrypt) 처리
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        userEntity.setPasswordChangeDate(LocalDateTime.now());
        userEntity.setPasswordErrorCount(0);
        userEntity.setLastLoginDate(LocalDateTime.now());

        userRepository.save(userEntity);

    }

}
