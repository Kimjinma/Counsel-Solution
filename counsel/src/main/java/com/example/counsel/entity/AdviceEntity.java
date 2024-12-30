package com.example.counsel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AdviceEntity {

    // Getter와 Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question1;
    private String question2;
    private String question3;
    private String schedule;

    // 기본 생성자 (필수)
    public AdviceEntity() {
    }

    // 매개변수를 받는 생성자
    public AdviceEntity(Long id, String question1, String question2, String question3, String schedule) {
        this.id = id;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.schedule = schedule;
    }

}
