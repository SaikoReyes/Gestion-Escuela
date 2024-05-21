package com.saiko.escuela.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return gradeRepository.findAll().stream()
            .collect(Collectors.toList());
    }

    // public Optional<GradeDTO> getGradeById(Long id){
    //     return gradeRepository.findById(id)
    //     .map(GradeMapper::toDTO);
    // }

    // public Optional<GradeDTO> getGradeByIdStudent(Long id){
    //     return gradeRepository.findById(id)
    //     .map(GradeMapper::toDTO);
    // }

    // public Optional<GradeDTO> getGradeByIdSubject(Long id){
    //     return gradeRepository.findById(id)
    //     .map(GradeMapper::toDTO);
    // }

    public ResponseEntity<Grade> saveGrade(Grade grade){
        grade.setCreatedDate(new Date());
        Grade savedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade);
    }

    // public boolean deleteGrade(Long id){
    //     if(gradeRepository.existsById(id)){
    //         gradeRepository.deleteById(id);
    //         return true;
    //     }
    //     else return false;
    // }

    // public Optional<Object> updateGrade(Long id, GradeDTO gradeDTO){
    //     if(gradeDTO.getGradeId()==null){
    //         throw new IllegalArgumentException("Grade ID is required");
    //     }
    //     return gradeRepository.findById(id)
    //     .map(existingGrade -> {
    //         existingGrade.setStudent(gradeDTO.getStudent());
    //         existingGrade.setSubject(gradeDTO.getSubject());
    //         existingGrade.setGrade(gradeDTO.getGrade());
    //         Grade updatedGrade = gradeRepository.save(existingGrade);
    //         return GradeMapper.toDTO(updatedGrade);
    //     });
    // }
    
}
