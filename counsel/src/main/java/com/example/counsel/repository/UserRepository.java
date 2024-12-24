package com.example.counsel.repository;

import com.example.counsel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);


    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
