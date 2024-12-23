package com.example.project.repository;


import com.example.project.entity.RequestEntity;
import com.example.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
     List<RequestEntity> findByStudentNo_StudentNo(String empNo); // 수정
     List<RequestEntity> findByCounselor_EmpNo(String empNo); // 수정

}
