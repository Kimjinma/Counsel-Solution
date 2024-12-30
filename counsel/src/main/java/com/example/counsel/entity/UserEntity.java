package com.example.counsel.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private String id;

    private String password;

    private String username;

    private String name;

    private String email;

    private String phone;

    private String role;
}
