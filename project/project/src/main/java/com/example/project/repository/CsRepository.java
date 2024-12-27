package com.example.project.repository;

import com.example.project.entity.CsEntity;
import com.example.project.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsRepository extends JpaRepository<CsEntity, String> {
    CsEntity findByQuestionId(String questionId);

}
