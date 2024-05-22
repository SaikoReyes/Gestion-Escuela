package com.saiko.escuela.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saiko.escuela.dto.StudentWithGrades;
import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.GradeId;
import com.saiko.escuela.entity.Student;
import com.saiko.escuela.repository.GradeRepository;

@Service
public class GradeService {
    
    private GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }

    public ResponseEntity<List<Grade>> getAllGrades(){
        return ResponseEntity.ok(gradeRepository.findAll().stream()
            .collect(Collectors.toList()));
    }

    public ResponseEntity<Grade> getGradeById(GradeId id){
        Optional<Grade> grade = gradeRepository.findByIdAll(id.getStudentId(), id.getSubjectId());
        if(grade.isPresent()){
            Grade deletedGrade = grade.get();
            gradeRepository.delete(deletedGrade);
            return ResponseEntity.ok(deletedGrade);
        }
        throw new IllegalArgumentException("Grade not found with id: " + id);
    }

    public ResponseEntity<Grade> saveGrade(Grade grade){
        grade.setCreatedDate(new Date());
        Grade savedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade);
    }

    public ResponseEntity<Grade> deleteGrade(GradeId id){
        Optional<Grade> grade = gradeRepository.findByIdAll(id.getStudentId(), id.getSubjectId());
        if(grade.isPresent()){
            Grade deletedGrade = grade.get();
            gradeRepository.delete(deletedGrade);
            return ResponseEntity.ok(deletedGrade);
        }
        throw new IllegalArgumentException("Grade not found with id: " + id);
    }

    public ResponseEntity<Grade> updateGrade(Grade grade){
        Optional<Grade> existingGrade = gradeRepository.findByIdAll(grade.getGradeId().getStudentId(), grade.getGradeId().getSubjectId());
        if(existingGrade.isPresent()){
            Grade updatedGrade = new Grade();
            updatedGrade.setGradeId(existingGrade.get().getGradeId());
            updatedGrade.setStudent(existingGrade.get().getStudent());
            updatedGrade.setSubject(existingGrade.get().getSubject());
            updatedGrade.setGrade(grade.getGrade());
            updatedGrade.setCreatedDate(new Date());
            gradeRepository.save(updatedGrade);
            return ResponseEntity.ok(updatedGrade);
        }
        throw new IllegalArgumentException("Grade not found with id: " + grade.getGradeId());
    }

    
    
}
