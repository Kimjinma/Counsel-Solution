package com.example.project.service;


import com.example.project.entity.RequestEntity;
import com.example.project.repository.RequestRepository;
import org.apache.coyote.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestService {
    @Autowired
    private RequestRepository RequestRepository;

    public List<RequestEntity> getCounselRequestsByStudentNo(String studentNo) {
        return RequestRepository.findBystudentNo_studentNo(studentNo);
    }

}
