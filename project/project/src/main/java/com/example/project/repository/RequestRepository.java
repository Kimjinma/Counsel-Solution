package com.example.project.repository;


import com.example.project.entity.CounselorEntity;
import com.example.project.entity.CsEntity;
import com.example.project.entity.RequestEntity;
import com.example.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
     List<RequestEntity> findByStudentNo_StudentNo(String studentNo); // 수정
     List<RequestEntity> findByEmp_EmpNo(String empNo); // 수정
     RequestEntity findByCnsNo(Integer cnsNo);
     RequestEntity findByEmp_EmpNoAndCnsNo(String empNo, Integer cnsNo);


}
