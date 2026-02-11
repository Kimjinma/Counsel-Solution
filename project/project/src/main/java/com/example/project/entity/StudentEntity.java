package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "STDNT_INFO")
public class StudentEntity {

    @Id
    @Column(name = "STDNT_NO", nullable = false, length = 10)
    private String studentNo;

    @ManyToOne
    @JoinColumn(name = "USER_NO", referencedColumnName = "USER_NO", insertable = false, updatable = false)
    private UserEntity user; // USER_INFO와 연결



    @Column(name = "EMAIL")
    private String email;


    @Column(name = "LGN_ID", nullable = false, length = 15)
    private String lgn;



    @Column(name = "MBL_TELNO", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "ADDR", nullable = false, length = 200)
    private String address;

    @Column(name = "STDNT_NM", nullable = false, length = 100)
    private String name;

    public StudentEntity() {}

    // Getters and Setters
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public String getLgn() { // 추가된 Getter
        return lgn;
    }

    public void setLgn(String lgn) {
        this.lgn = lgn;
    }
    }