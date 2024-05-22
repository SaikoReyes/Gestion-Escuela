package com.saiko.escuela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.saiko.escuela.dto.StudentDTO;
import com.saiko.escuela.dto.StudentWithGrades;
import com.saiko.escuela.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.saiko.escuela.exception.NotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/student")
public class StudentContoller {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/get-all")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get-by-id/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id).orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }
    @DeleteMapping("/delete-student/{id}")
    public StudentDTO deleteStudent(@PathVariable Long id){
        if (studentService.getStudentById(id).isPresent()){
            StudentDTO studentDTO = studentService.getStudentById(id).get();
            studentService.deleteStudent(id);
            return studentDTO;
        }
        throw new NotFoundException("Student not found with id: " + id);
    }

    @PostMapping("/save-student")
    public StudentDTO saveStudent(@Valid @RequestBody StudentDTO studentDTO){ 
        return studentService.saveStudent(studentDTO);
    
    }

    @PostMapping("/update-student")
    public StudentDTO updateStudent(@Valid @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(studentDTO.getStudentId(), studentDTO)
        .orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @GetMapping("/get-student-grades/{id}")
    public ResponseEntity<StudentWithGrades> getMethodName(@PathVariable Long id) {
        return studentService.getStudentsWithGrades(id);
    }
    
}