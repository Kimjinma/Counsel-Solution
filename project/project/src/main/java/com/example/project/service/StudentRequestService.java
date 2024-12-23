package com.example.project.service;


import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentRequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<RequestEntity> getRequestsByStudentNo(String studentNo) {
        return requestRepository.findByStudentNo_StudentNo(studentNo);
    }
}
