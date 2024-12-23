package com.example.project.service;

import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 전달된 username 값 로그 출력
        System.out.println("Input username from Spring Security: " + username);

        // DB에서 username(lgn_id)에 해당하는 사용자 정보 조회
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 조회된 사용자 정보 로그 출력
        System.out.println("UserEntity found: " + userEntity);

        // Spring Security의 UserDetails 객체 생성
        return User.builder()
                .username(userEntity.getUsername()) // 로그인 아이디
                .password(userEntity.getPassword()) // 비밀번호
                .roles(userEntity.getUserSe().toUpperCase()) // 권한
                .build();
    }
}