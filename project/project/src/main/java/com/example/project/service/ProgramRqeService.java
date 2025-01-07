package com.example.project.service;


import com.example.project.dto.ProgramProDTO;
import com.example.project.dto.StudentParticipationDto;
import com.example.project.entity.*;
import com.example.project.repository.ProRepository;
import com.example.project.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramRqeService {

    @Autowired
    private ProRepository proRepository;

    @Autowired
    private ProgramRepository programRepository;


    // 생성자
    public ProgramRqeService(ProRepository proRepository, ProgramRepository programRepository ) {
        this.proRepository = proRepository;
        this.programRepository = programRepository;
    }

    // 직원 번호로 요청 정보 조회
    public List<ProgramProDTO> getRequestsByEmpNo(String empNo) {
        List<ProgramProEntity> entities = proRepository.findByEmp_EmpNo(empNo);
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    // 엔티티를 DTO로 변환
    public ProgramProDTO toDto(ProgramProEntity entity) {
        ProgramProDTO dto = new ProgramProDTO();

        if (entity != null) {
            dto.setProgramNumber(entity.getProgramNo());
            dto.setProgramName(entity.getProgramName());

            ProgramEntity programEntity = programRepository.findByProgramName(entity.getProgramName());
            if (programEntity != null) {
                dto.setEduplcnm(programEntity.getEducationPlace());
                dto.setRegistrationDate(programEntity.getDecisionDate());
                dto.setPlannope(programEntity.getPlanCapacity());
            }
        }

        return dto;
    }

    // 특정 프로그램 번호로 학생 리스트 조회
    public List<StudentParticipationDto> getStudentListByProgramNo(int programNo) {
        // 1. 프로그램 번호로 프로그램 신청 정보 조회
        List<ProgramProEntity> programApplications = proRepository.findByProgramNo(programNo);
        if (programApplications.isEmpty()) {
            throw new IllegalArgumentException("No student applications found for programNo: " + programNo);
        }

        // 2. 학생 정보를 DTO로 변환
        return programApplications.stream()
                .map(application -> {
                    StudentEntity student = application.getStudent();
                    if (student == null) {
                        throw new IllegalArgumentException("Student not found for programNo: " + application.getProgramNo());
                    }
                    StudentParticipationDto dto = new StudentParticipationDto();
                    dto.setStudentNo(student.getStudentNo());
                    dto.setName(student.getName());
                    dto.setApprovalStatus(application.getApprovalStatus());
                    dto.setProgramNumber(application.getProgramNo()); // programNumber 추가 설정

                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public boolean approveStudent(String studentNo, int programNo) {
        ProgramProEntity programPro = proRepository.findByStudent_StudentNoAndProgramNo(studentNo, programNo);
        System.out.println("Before Update Approval Status: " + programPro.getApprovalStatus());

        if (programPro == null) {
            System.err.println("Entity not found for studentNo: " + studentNo + ", programNo: " + programNo);
            throw new IllegalArgumentException("해당 학생 또는 프로그램을 찾을 수 없습니다.");
        }

        if ("Y".equals(programPro.getApprovalStatus())) {
            return false; // 이미 승인된 상태
        }

        programPro.setApprovalStatus("Y");
        proRepository.save(programPro);
        System.out.println("After Update Approval Status: " + programPro.getApprovalStatus());
        return true;
    }


    public List<StudentParticipationDto> getApprovedParticipants(int programNo) {
        // 승인된 학생들만 조회
        List<ProgramProEntity> approvedApplications = proRepository.findByProgramNoAndApprovalStatus(programNo, "Y");

        // DTO로 변환
        return approvedApplications.stream()
                .map(application -> {
                    StudentEntity student = application.getStudent();
                    StudentParticipationDto dto = new StudentParticipationDto();
                    dto.setStudentNo(student.getStudentNo());
                    dto.setName(student.getName());
                    dto.setApprovalStatus(application.getApprovalStatus());
                    dto.setProgramNumber(application.getProgramNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean cancelStudent(String studentNo, int programNo) {
        ProgramProEntity programPro = proRepository.findByStudent_StudentNoAndProgramNo(studentNo, programNo);

        if (programPro == null) {
            throw new IllegalArgumentException("해당 학생 또는 프로그램을 찾을 수 없습니다.");
        }

        if (!"Y".equals(programPro.getApprovalStatus())) {
            return false; // 이미 취소된 상태
        }


        programPro.setApprovalStatus("N");
        proRepository.save(programPro);
        System.out.println("After Update Approval Status: " + programPro.getApprovalStatus());
        return true;
    }

}