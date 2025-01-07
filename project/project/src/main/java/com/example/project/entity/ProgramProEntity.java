package com.example.project.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRGRM_PRGRS")
public class ProgramProEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRGRM_NO")
    private Integer programNo;

    @Column(name = "PRGRM_NM", length = 100)
    private String programName;



    @Column(name = "APRV_YN", length = 1)
    private String approvalStatus;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "APPLY_YN", length = 1)
    private String applyStatus;

    @Column(name = "ENGAGE_YN", length = 1)
    private String engageStatus;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "PRGRM_NM", insertable = false, updatable = false)
    private ProgramEntity program;
    @ManyToOne
    @JoinColumn(name = "STDNT_NO", insertable = false, updatable = false)
    private StudentEntity student;


    // Getters and Setters
    @ManyToOne
    @JoinColumn(name = "EMP_NO", insertable = false, updatable = false)
    private CounselorEntity emp;
}
