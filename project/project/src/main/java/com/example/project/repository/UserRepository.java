package com.example.project.repository;

import com.example.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // username 필드를 기준으로 조회
    UserEntity findByUserNo(String userNo);
    boolean existsByUserNo(String userNo);
    UserEntity findByUsername(String username);

}
