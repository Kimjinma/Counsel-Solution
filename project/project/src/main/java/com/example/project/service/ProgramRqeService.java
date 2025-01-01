package com.example.project.service;


import com.example.project.dto.ProgramProDTO;
import com.example.project.entity.ProgramProEntity;
import com.example.project.repository.ProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramRqeService {


    @Autowired
    private ProRepository proRepository;

    public List<ProgramProDTO> getRequestsByStudentNo(String studentNo) {
        // RequestEntity에서 데이터 조회 및 변환
        List<ProgramProEntity> entities = proRepository.findByStudent_StudentNo(studentNo);
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProgramProDTO toDto(ProgramProEntity entity) {
        ProgramProDTO dto = new ProgramProDTO();
        dto.setProgramNumber(entity.getProgram() != null ? entity.getProgram().getProgramNumber() : 0); // 프로그램 번호 추출
        dto.setProgramName(entity.getProgram() != null ? entity.getProgram().getProgramName() : null); // 프로그램 이름 추출
        dto.setPlannope(entity.getProgram() != null ? entity.getProgram().getMaxParticipants() : null); // 프로그램 참여자
        dto.setEduplcnm(entity.getProgram() != null ? entity.getProgram().getVenue() : null); // 프로그램 장소 추출
        dto.setRegistrationDate(entity.getProgram() != null ? entity.getProgram().getStartDate() : null); // 프로그램 장소 추출

        dto.setApplyYn(entity.getApplyYn());
        return dto;
    }



}

