package com.saiko.escuela.dto;

import java.util.Date;
import java.util.List;

import com.saiko.escuela.entity.Grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithGrades {
    private Long id;
    private String studentName;
    private String studentLastName;
    private String studentEmail;
    private String studentPhone;
    private double averageGrade;
    private Date createDate;
    private List<Grade> grades;
}