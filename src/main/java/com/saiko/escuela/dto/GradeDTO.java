package com.saiko.escuela.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private Long gradeId;
    private StudentDTO student;
    private SubjectDTO subjectDTO;
    private double grade;
}
