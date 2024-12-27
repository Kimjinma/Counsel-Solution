package com.example.project.repository;

import com.example.project.entity.CS_ANSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSQRepository extends JpaRepository<CS_ANSEntity, Integer> {
    CS_ANSEntity findByStudent_StudentNo(String studentNo);


}
