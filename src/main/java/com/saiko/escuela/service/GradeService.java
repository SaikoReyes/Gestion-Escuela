package com.saiko.escuela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saiko.escuela.dto.GradeDTO;
import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.repository.GradeRepository;

@Service
public class GradeService {
    
    private GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

    public Grade getGradeById(Long gradeId){
        return gradeRepository.findById(gradeId).orElse(null);
    }

    public Grade saveGrade(Grade grade){
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId){
        gradeRepository.deleteById(gradeId);
    }

    public Grade updateGrade(Grade grade){
        return gradeRepository.save(grade);
    }
    
}
