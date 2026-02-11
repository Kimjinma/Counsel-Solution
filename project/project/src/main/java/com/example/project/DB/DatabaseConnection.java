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
        dataSource.setUsername("root"); // 사용자명
        dataSource.setPassword("wls0264"); // 비밀번호
        return dataSource;
    }
}