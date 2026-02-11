package com.example.project.repository;


import com.example.project.entity.ProgramProEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProRepository extends JpaRepository <ProgramProEntity, Integer>{

    List<ProgramProEntity> findByEmp_EmpNo(String EmpNo);
    List<ProgramProEntity> findByProgramNo(Integer programNo);
    List<ProgramProEntity> findByStudent_StudentNo(String studentNo);

    ProgramProEntity findByStudent_StudentNoAndProgramNo(String studentNo, int programNo);
    List<ProgramProEntity> findByProgramNoAndApprovalStatus(int programNo, String approvalStatus);


}


