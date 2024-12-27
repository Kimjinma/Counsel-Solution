package com.example.project.service;

import com.example.project.dto.CsDTO;
import com.example.project.dto.CounselRequestDTO;
import com.example.project.dto.UpdateUserDTO;
import com.example.project.entity.CS_ANSEntity;
import com.example.project.entity.RequestEntity;
import com.example.project.entity.StudentEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.CSQRepository;
import com.example.project.repository.StudentRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CSQService {
    @Autowired
    private CSQRepository csqRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

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

    }
}