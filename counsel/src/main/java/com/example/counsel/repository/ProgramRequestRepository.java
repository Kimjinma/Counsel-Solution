package com.example.counsel.repository;

import com.example.counsel.entity.ProgramRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRequestRepository extends JpaRepository<ProgramRequest, Long> {
    List<ProgramRequest> findByStudentNo(String studentNo);

    boolean existsByStudentNoAndProgramNo(String studentNo, Long programNo);
}
