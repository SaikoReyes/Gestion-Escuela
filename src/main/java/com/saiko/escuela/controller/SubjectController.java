package com.saiko.escuela.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saiko.escuela.dto.SubjectDTO;
import com.saiko.escuela.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/get-all")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Optional<SubjectDTO>> getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping("/save-subject")
    public ResponseEntity<SubjectDTO> saveSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubject(subjectDTO);
    }

    @DeleteMapping("/delete-subject/{id}")
    public ResponseEntity<SubjectDTO> deleteSubject(@PathVariable Long id){
        return subjectService.deleteSubject(id);
    }

    @PutMapping("/update-subject")
    public ResponseEntity<Optional<SubjectDTO>> updateSubject(@Valid @RequestBody SubjectDTO subjectDTO){
        return subjectService.updateSubject(subjectDTO.getSubjectId(), subjectDTO);
    }
    
}
