package com.saiko.escuela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saiko.escuela.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
    
}