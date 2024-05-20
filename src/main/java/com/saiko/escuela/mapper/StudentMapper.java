package com.saiko.escuela.mapper;

import com.saiko.escuela.dto.StudentDTO;
import com.saiko.escuela.entity.Student;

public class StudentMapper {
    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setStudentName(student.getStudentName());
        dto.setStudentLastName(student.getStudentLastName());
        dto.setStudentEmail(student.getStudentEmail());
        dto.setStudentPhone(student.getStudentPhone());
        dto.setAverageGrade(student.getAverageGrade());
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setStudentName(dto.getStudentName());
        student.setStudentLastName(dto.getStudentLastName());
        student.setStudentEmail(dto.getStudentEmail());
        student.setStudentPhone(dto.getStudentPhone());
        student.setAverageGrade(dto.getAverageGrade());
        return student;
    }
}

