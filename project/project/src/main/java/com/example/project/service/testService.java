package com.example.project.service;

import com.example.project.entity.StudentEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.StudentRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class testService {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private StudentRepository studentRepository;

        /**
         * 학번 조회 메서드
         */
        public String getStudentNoByLoginId(String username) {
            // 1. 사용자 테이블에서 사용자 정보 조회
            UserEntity user = userRepository.findByUsername(username);
            if (user == null) {
                // 사용자 정보가 없을 경우 null 반환 또는 예외 처리
                return null; // 혹은 throw new IllegalArgumentException("해당 로그인 아이디를 가진 사용자가 없습니다: " + loginId);
            }

            // 2. 사용자 번호로 학생 정보 조회
            StudentEntity student = studentRepository.findByStudentNo(user.getUserNo());
            if (student == null) {
                // 학생 정보가 없을 경우 null 반환 또는 예외 처리
                return null; // 혹은 throw new IllegalArgumentException("해당 사용자 번호를 가진 학생이 없습니다: " + user.getUserNo());
            }

            // 3. 학생 학번 반환
            return student.getStudentNo();
        }
    }

