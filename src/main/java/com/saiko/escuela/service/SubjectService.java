package com.saiko.escuela.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saiko.escuela.dto.SubjectDTO;
import com.saiko.escuela.entity.Subject;
import com.saiko.escuela.mapper.SubjectMapper;
import com.saiko.escuela.repository.SubjectRepository;

@Service
public class SubjectService{

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDTO> getAllSubjects(){
        return subjectRepository.findAll().stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubjectDTO> getSubjectById(Long id){
        return subjectRepository.findById(id)
        .map(SubjectMapper::toDTO);
    }

    public SubjectDTO saveSubject(SubjectDTO subjectDTO){
        Subject subject = SubjectMapper.toEntity(subjectDTO);
        Subject savedSubject = subjectRepository.save(subject);
        return SubjectMapper.toDTO(savedSubject);
    }

    public boolean deleteSubject(Long id){
        if(subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    public Optional<SubjectDTO> updateSubject(Long id,SubjectDTO subjectDTO){
        return subjectRepository.findById(id)
        .map(existingSubject -> {
            existingSubject.setSubjectName(subjectDTO.getSubjectName());
            Subject updatedSubject = subjectRepository.save(existingSubject);
            return SubjectMapper.toDTO(updatedSubject);
        });
    }
    
}
