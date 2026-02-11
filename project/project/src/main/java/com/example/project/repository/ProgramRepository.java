package com.example.project.repository;

import com.example.project.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramEntity, String> {
    ProgramEntity findByProgramName(String programName);

}