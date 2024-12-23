package com.example.project.repository;


import com.example.project.entity.RequestEntity;
import com.example.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
        StudentEntity findByUser_UserNo(String userNo);
}

