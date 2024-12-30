package com.example.counsel.repository;

import com.example.counsel.entity.ProgramProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository
@Repository
public interface ProgramProgressRepository extends JpaRepository<ProgramProgress, String> {
    boolean existsByPrgrmNoAndStdntNo(Long prgrmNo, String stdntNo);
}
