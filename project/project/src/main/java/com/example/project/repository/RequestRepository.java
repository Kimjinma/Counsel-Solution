package com.example.project.repository;


import com.example.project.entity.RequestEntity;
import com.example.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
     /*RequestEntity findByCounselor_EMPNO(String empNo);*/
     List<RequestEntity> findBystudentNo_studentNo(String studentNo);

}
