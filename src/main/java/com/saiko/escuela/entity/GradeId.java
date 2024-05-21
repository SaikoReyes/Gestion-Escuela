package com.saiko.escuela.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class GradeId implements Serializable{
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "subject_id")
    private Long subjectId;
}
