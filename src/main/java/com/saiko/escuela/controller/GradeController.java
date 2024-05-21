package com.saiko.escuela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.saiko.escuela.entity.Grade;

import java.util.List;
import com.saiko.escuela.service.GradeService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }

    @GetMapping("/get-all")
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    // @GetMapping("/get-by-id/{id}")
    // public GradeDTO getGradeById(@PathVariable Long id){
    //     return gradeService.getGradeById(id).orElseThrow(() -> new NotFoundException("Grade not found with id: " + id));
    // }
    
    // @GetMapping("/get-grades-by-student/{id}")
    // public String getMethodName(@PathVariable Long id) {
    //     return new String();
    // }

    @PostMapping("/save-grade")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }
    
    

}
