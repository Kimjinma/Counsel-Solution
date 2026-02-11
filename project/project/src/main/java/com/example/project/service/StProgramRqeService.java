package com.example.project.service;

import com.example.project.dto.ProgramProDTO;
import com.example.project.entity.ProgramEntity;
import com.example.project.entity.ProgramProEntity;
import com.example.project.repository.ProRepository;
import com.example.project.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StProgramRqeService {

    @Autowired
    private ProRepository proRepository;

    @Autowired
    private ProgramRepository programRepository;

    // 생성자
    public StProgramRqeService(ProRepository proRepository, ProgramRepository programRepository) {
        this.proRepository = proRepository;
        this.programRepository = programRepository;
    }

    // 학생 번호로 요청 정보 조회
    public List<ProgramProDTO> getRequestsByStudentNo(String StudentNo) {
        System.out.println("Fetching data for StudentNo: " + StudentNo);

        List<ProgramProEntity> entities = proRepository.findByStudent_StudentNo(StudentNo);
        System.out.println("Fetched entities: " + entities);
        System.out.println("Number of entities: " + entities.size());

        if (entities.isEmpty()) {
            System.err.println("No data found for StudentNo: " + StudentNo);
        }

        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }


    // 엔티티를 DTO로 변환
    public ProgramProDTO toDto(ProgramProEntity entity) {
        ProgramProDTO dto = new ProgramProDTO();

        if (entity != null) {
            dto.setProgramNumber(entity.getProgramNo());
            dto.setProgramName(entity.getProgramName());
            dto.setApprovalStatus(entity.getApprovalStatus());

            ProgramEntity programEntity = programRepository.findByProgramName(entity.getProgramName());
            if (programEntity != null) {
                dto.setEduplcnm(programEntity.getEducationPlace());
                dto.setRegistrationDate(programEntity.getDecisionDate());
                dto.setPlannope(programEntity.getPlanCapacity());
            } else {
                System.err.println("No ProgramEntity found for programName: " + entity.getProgramName());
            }
        }

        return dto;
    }
}
