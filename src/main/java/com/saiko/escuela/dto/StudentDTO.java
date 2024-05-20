package com.saiko.escuela.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {
    private Long studentId;

    @NotNull(message = "Student name is required")
    private String studentName;

    @NotNull(message = "Student last name is required")
    private String studentLastName;

    @NotNull(message = "Student email is required")
    private String studentEmail;

    @NotNull(message = "Student phone is required")
    private String studentPhone;
    private double averageGrade;
}
