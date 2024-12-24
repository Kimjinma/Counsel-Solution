package com.example.counsel.repository;

import com.example.counsel.entity.ProgramRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRequestRepository extends JpaRepository<ProgramRequestEntity, Long> {
    List<ProgramRequestEntity> findByStudentNo(String studentNo);
}