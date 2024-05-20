package com.saiko.escuela.mapper;

import com.saiko.escuela.dto.SubjectDTO;
import com.saiko.escuela.entity.Subject;

public class SubjectMapper {

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setSubjectId(subject.getSubjectId());
        dto.setSubjectName(subject.getSubjectName());
        return dto;
    }

    public static Subject toEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setSubjectId(dto.getSubjectId());
        subject.setSubjectName(dto.getSubjectName());
        return subject;
    }
}
