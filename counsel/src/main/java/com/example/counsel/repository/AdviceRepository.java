package com.example.counsel.repository;


import com.example.counsel.entity.AdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<AdviceEntity, Long> {
}

