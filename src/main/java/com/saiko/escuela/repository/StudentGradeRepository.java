package com.saiko.escuela.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.GradeId;
import com.saiko.escuela.entity.Student;

@Repository
public interface StudentGradeRepository extends JpaRepository<Grade, GradeId>{
    @Query("SELECT u FROM Grade u WHERE u.student = :student")
    List<Grade> findGradesByStudent(@Param("student") Student student);

}
