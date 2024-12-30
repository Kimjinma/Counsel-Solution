package com.example.project.repository;

import com.example.project.entity.CounselorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselorRepository extends JpaRepository<CounselorEntity, String> {
    CounselorEntity findByLoginId(String loginId);
    CounselorEntity findByUser_UserNo(String userNo);

    CounselorEntity findByEmpNo(String empNo);
}
