package com.example.counsel.repository;

import com.example.counsel.entity.ProgramProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository
@Repository
public interface ProgramProgressRepository extends JpaRepository<ProgramProgress, Long> {
    List<ProgramProgress> findByStdntNo(String stdntNo);
    boolean existsByStdntNoAndPrgrmNo(String stdntNo, Long prgrmNo);
}
