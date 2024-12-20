/*
package com.example.project.service;

import com.example.project.entity.StudentEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity findByUserNo(String userNo) {
        return studentRepository.findByUser_UserNo(userNo);
    }

    public StudentEntity findByUsername(String username, UserEntity userEntity) {
        return studentRepository.findByUser_UserNo(userEntity.getUserNo());
    }
}*/
