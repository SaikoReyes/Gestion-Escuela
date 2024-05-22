package com.saiko.escuela.service;

import com.saiko.escuela.dto.StudentDTO;
import com.saiko.escuela.dto.StudentWithGrades;
import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.Student;
import com.saiko.escuela.mapper.StudentMapper;
import com.saiko.escuela.repository.StudentGradeRepository;
import com.saiko.escuela.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentGradeRepository studentGradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,StudentGradeRepository studentGradeRepository) {
        this.studentGradeRepository = studentGradeRepository;
        this.studentRepository = studentRepository;
    }


    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDTO);
    }

    public Optional<StudentDTO> updateStudent(Long id, StudentDTO studentDTO) {
        if(studentDTO.getStudentId()==null){
            throw new IllegalArgumentException("Student ID is required");
        }
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setStudentName(studentDTO.getStudentName());
                    existingStudent.setStudentLastName(studentDTO.getStudentLastName());
                    existingStudent.setStudentEmail(studentDTO.getStudentEmail());
                    existingStudent.setStudentPhone(studentDTO.getStudentPhone());
                    existingStudent.setAverageGrade(studentDTO.getAverageGrade());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return StudentMapper.toDTO(updatedStudent);
                });
    }

    public boolean deleteStudent(Long id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    public ResponseEntity<StudentWithGrades> getStudentsWithGrades(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            Student studentData = student.get();
            List<Grade> grades = studentGradeRepository.findGradesByStudent(studentData);
            StudentWithGrades studentWithGrades = new StudentWithGrades(
                    studentData.getStudentId(),
                    studentData.getStudentName(),
                    studentData.getStudentLastName(),
                    studentData.getStudentEmail(),
                    studentData.getStudentPhone(),
                    studentData.getAverageGrade(),
                    new Date(),
                    grades
                    );
            return ResponseEntity.ok(studentWithGrades);
        }
        throw new IllegalArgumentException("Student not found with id: " + id);
    }

}
