package com.saiko.escuela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saiko.escuela.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
