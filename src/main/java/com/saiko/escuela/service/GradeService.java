package com.saiko.escuela.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.GradeId;
import com.saiko.escuela.entity.Student;
import com.saiko.escuela.repository.GradeRepository;
import com.saiko.escuela.repository.StudentGradeRepository;
import com.saiko.escuela.repository.StudentRepository;
import com.saiko.escuela.utils.AverageGrade;

@Service
public class GradeService {
    
    private GradeRepository gradeRepository;
    private StudentGradeRepository studentGradeRepository;
    private StudentRepository studentRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, StudentGradeRepository studentGradeRepository, StudentRepository studentRepository){
        this.gradeRepository = gradeRepository;
        this.studentGradeRepository = studentGradeRepository;
        this.studentRepository = studentRepository;
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
        Optional<Student> student = studentRepository.findById(grade.getStudent().getStudentId());
        if(student.isPresent()){
            if(studentGradeRepository.findGradesByStudent(student.get()).size()>0)
            {
                List<Grade> grades = studentGradeRepository.findGradesByStudent(student.get());
                student.get().setAverageGrade(AverageGrade.calculateAverageWithNewGrade(grades,grade.getGrade()));
                studentRepository.save(student.get());
            }
            else{
                student.get().setAverageGrade(grade.getGrade());
                studentRepository.save(student.get());
            }
            grade.setCreatedDate(new Date());
            Grade savedGrade = gradeRepository.save(grade);
            return ResponseEntity.ok(savedGrade);
        }
        throw new IllegalArgumentException("Student not found with id: " + grade.getStudent().getStudentId());
        
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
