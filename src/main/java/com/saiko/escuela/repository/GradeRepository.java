package com.saiko.escuela.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saiko.escuela.entity.Grade;
import com.saiko.escuela.entity.GradeId;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId>{
    
    @Query("SELECT u FROM Grade u WHERE u.id.studentId = :studentId AND u.id.subjectId = :subjectId")
    Optional<Grade> findByIdAll(@Param("studentId") int studentId, @Param("subjectId") int subjectId);
}
