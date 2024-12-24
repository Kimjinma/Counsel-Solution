package com.example.counsel.repository;

import com.example.counsel.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, String> {
    // 추가적인 메서드가 필요하면 정의
}
