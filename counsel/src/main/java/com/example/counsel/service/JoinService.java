package com.example.counsel.service;

import com.example.counsel.dto.JoinDTO;
import com.example.counsel.entity.UserEntity;
import com.example.counsel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder; // PasswordEncoder 타입으로 변경

    public void joinProcess(JoinDTO joinDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); // 비밀번호 암호화
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
