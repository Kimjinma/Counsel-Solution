package com.example.project.service;

import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.StudentEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.StudentRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional

    // findByUsername 메서드 구현
    public UserEntity findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        return userEntity;
    }
    @Transactional
    public void updateStudent(String username, UpdateUserDTO updateUserDTO) {
        // UserEntity 조회 (username 기준)
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found with userNo: " + username);
        }

        // StudentEntity 조회
        StudentEntity studentEntity = studentRepository.findByUser_UserNo(userEntity.getUserNo());
        if (studentEntity == null) {
            throw new IllegalArgumentException("Student not found with USER_NO: " + userEntity.getUserNo());
        }

        // 비밀번호 업데이트 (평문 저장)
        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isEmpty()) {
            userEntity.setPassword(updateUserDTO.getPassword());
        }

        // 이메일 업데이트
        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isEmpty()) {
            studentEntity.setEmail(updateUserDTO.getEmail());
        }

        // 주소 업데이트
        if (updateUserDTO.getAddress() != null && !updateUserDTO.getAddress().isEmpty()) {
            studentEntity.setAddress(updateUserDTO.getAddress());
        }

        // 전화번호 업데이트
        if (updateUserDTO.getPhoneNumber() != null) {
            studentEntity.setMobileNumber(updateUserDTO.getPhoneNumber());
        }

        // 저장소에 업데이트된 정보 저장
        userRepository.save(userEntity);
        studentRepository.save(studentEntity);
    }

}
