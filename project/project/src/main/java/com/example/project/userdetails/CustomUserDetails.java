package com.example.project.userdetails;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails extends User {
    private final String studentNo;

    public CustomUserDetails(String username, String password, String role, String studentNo) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority(role))); // 권한 변환
        this.studentNo = studentNo;

    }

    public String getStudentNo() {
        System.out.println("CustomUserDetails - studentNo: " + this.studentNo);

        return this.studentNo;
    }

}
