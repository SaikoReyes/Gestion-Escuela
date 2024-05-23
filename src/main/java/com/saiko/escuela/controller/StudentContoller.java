package com.saiko.escuela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.saiko.escuela.dto.StudentDTO;
import com.saiko.escuela.dto.StudentWithGrades;
import com.saiko.escuela.service.StudentService;

import jakarta.validation.Valid;

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

@RestController
@RequestMapping("/student")
public class StudentContoller {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/get-all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Optional<StudentDTO>> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @PostMapping("/save-student")
    public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentDTO){ 
        return studentService.saveStudent(studentDTO);
    
    }

    @PutMapping("/update-student")
    public ResponseEntity<Optional<StudentDTO>> updateStudent(@Valid @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(studentDTO.getStudentId(), studentDTO);
    }

    @GetMapping("/get-student-grades/{id}")
    public ResponseEntity<StudentWithGrades> getMethodName(@PathVariable Long id) {
        return studentService.getStudentsWithGrades(id);
    }
    
}