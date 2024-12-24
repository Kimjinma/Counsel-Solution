package com.example.counsel.service;

import com.example.counsel.entity.UserEntity;
import com.example.counsel.repository.UserRepository;
import com.example.counsel.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public boolean resetPassword(String email) {
        // 사용자 검색
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return false; // 이메일이 존재하지 않음
        }

        // 임시 비밀번호 생성
        String temporaryPassword = PasswordUtils.generateTemporaryPassword();

        // 임시 비밀번호 암호화 및 저장
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        userRepository.save(user);

        // 이메일 발송
        String subject = "임시 비밀번호 안내";
        String body = "안녕하세요, " + user.getName() + "님.\n\n"
                + "임시 비밀번호는 다음과 같습니다:\n"
                + temporaryPassword + "\n\n"
                + "로그인 후 반드시 비밀번호를 변경해 주세요.\n\n"
                + "감사합니다.";
        emailService.sendEmail(user.getEmail(), subject, body);

        return true;
    }
}
