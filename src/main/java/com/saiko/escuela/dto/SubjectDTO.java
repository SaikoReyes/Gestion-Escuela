package com.saiko.escuela.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectDTO {
    private Long subjectId;

    @NotNull(message = "Subject name is required")
    private String subjectName;
}
