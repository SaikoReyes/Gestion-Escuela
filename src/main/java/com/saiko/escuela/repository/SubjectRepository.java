package com.saiko.escuela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saiko.escuela.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
