package com.saiko.escuela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.GradeId;
import java.util.List;
import com.saiko.escuela.service.GradeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Grade>> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Grade> getGradeById(@RequestBody GradeId id){
        return gradeService.getGradeById(id);
    }
    
    @PostMapping("/save-grade")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }

    @DeleteMapping("/delete-grade")
    public ResponseEntity<Grade> deleteGrade(@RequestBody GradeId id){
        return gradeService.deleteGrade(id);
    }

    @PutMapping("/update-grade")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade) {
        return gradeService.updateGrade(grade);
    }

}
