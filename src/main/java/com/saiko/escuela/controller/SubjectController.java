package com.saiko.escuela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saiko.escuela.dto.SubjectDTO;
import com.saiko.escuela.exception.NotFoundException;
import com.saiko.escuela.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/get-all")
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/get-by-id/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id).orElseThrow(() -> new NotFoundException("Subject not found with id: " + id));
    }

    @PostMapping("/save-subject")
    public SubjectDTO saveSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubject(subjectDTO);
    }

    @DeleteMapping("/delete-subject/{id}")
    public SubjectDTO deleteSubject(@PathVariable Long id){
        if(subjectService.getSubjectById(id).isPresent()){
            SubjectDTO subjectDTO = subjectService.getSubjectById(id).get();
            subjectService.deleteSubject(id);
            return subjectDTO;
        }
        throw new NotFoundException("Subject not found with id: " + id);
    }

    @PostMapping("/update-subject")
    public SubjectDTO updateSubject(@Valid @RequestBody SubjectDTO subjectDTO){
        return subjectService.updateSubject(subjectDTO.getSubjectId(), subjectDTO)
        .orElseThrow(() -> new NotFoundException("Subject not found"));
    }
    
    
}
