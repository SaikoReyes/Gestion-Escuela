package com.saiko.escuela.dto;

import lombok.Data;

@Data
public class GradesDTO {
    private Long gradeId;
    private StudentDTO student;
    private SubjectDTO subjectDTO;
    private double grade;
}
