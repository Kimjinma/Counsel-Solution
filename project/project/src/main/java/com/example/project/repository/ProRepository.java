package com.example.project.repository;


import com.example.project.entity.ProgramEntity;
import com.example.project.entity.ProgramProEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProRepository extends JpaRepository <ProgramEntity, String>{

    List<ProgramProEntity> findByStudent_StudentNo(String studentNo); // 수정


}
