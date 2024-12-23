package com.example.project.service;

import com.example.project.entity.StudentEntity;
import com.example.project.entity.UserEntity;
import com.example.project.repository.StudentRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentManagementService {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private StudentRepository studentRepository;

        /**
         * 학번 조회 메서드
         */
        public String getStudentNoByLoginId(String username) {
            // 1. 사용자 정보 조회
            UserEntity user = userRepository.findByUsername(username);
            if (user == null) {
                throw new IllegalArgumentException("해당 로그인 아이디를 가진 사용자가 없습니다: " + username);
            }

            // 2. 사용자 번호로 학생 정보 조회
            StudentEntity student = studentRepository.findByUser_UserNo(user.getUserNo());
            if (student == null) {
                throw new IllegalArgumentException("해당 사용자 번호를 가진 학생이 없습니다: " + user.getUserNo());
            }

            // 3. 학생 학번 반환
            return student.getStudentNo();
        }

}

