package com.example.project.DB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConnection {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 클래스
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/pro"); // 데이터베이스 URL
        // 환경 변수나 설정 파일에서 값을 불러오도록 처리하는 것이 안전합니다.
        dataSource.setUsername(System.getenv("DB_USERNAME") != null ? System.getenv("DB_USERNAME") : "root");
        dataSource.setPassword(System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "1234"); // 실제 패스워드
                                                                                                              // 하드코딩 제거
        return dataSource;
    }
}