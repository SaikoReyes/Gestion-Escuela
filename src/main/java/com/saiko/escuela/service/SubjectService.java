package com.saiko.escuela.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saiko.escuela.entity.Subject;
import com.saiko.escuela.repository.SubjectRepository;

@Service
public class SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long subjectId){
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }

    public Subject updateSubject(Subject subject){
        return subjectRepository.save(subject);
    }
    
}
