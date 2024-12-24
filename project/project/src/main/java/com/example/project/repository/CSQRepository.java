package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSQRepository extends JpaRepository<CSQITEMEnntity, Integer> {
    CSQITEMEnntity findByStudent_StudentNo(String studentNo);


}
