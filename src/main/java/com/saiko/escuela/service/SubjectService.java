package com.saiko.escuela.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saiko.escuela.dto.SubjectDTO;
import com.saiko.escuela.entity.Subject;
import com.saiko.escuela.exception.NotFoundException;
import com.saiko.escuela.mapper.SubjectMapper;
import com.saiko.escuela.repository.SubjectRepository;

@Service
public class SubjectService{

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public ResponseEntity<List<SubjectDTO>> getAllSubjects(){
        List<Subject> subjects = subjectRepository.findAll();
        if(!subjects.isEmpty()){
            return ResponseEntity.ok(subjects.stream()
                .map(SubjectMapper::toDTO)
                .collect(Collectors.toList()));
        }
        else throw new NotFoundException("No subjects found");
    }

    public ResponseEntity<Optional<SubjectDTO>> getSubjectById(Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()){
            return ResponseEntity.ok(subjectRepository.findById(id)
                .map(SubjectMapper::toDTO));
        }
        else throw new NotFoundException("Subject not found with id: " + id);
    }

    public ResponseEntity<SubjectDTO> saveSubject(SubjectDTO subjectDTO){
        Subject subject = SubjectMapper.toEntity(subjectDTO);
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(SubjectMapper.toDTO(savedSubject));
    }

    public ResponseEntity<SubjectDTO> deleteSubject(Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()){
            SubjectDTO subjectDTO = SubjectMapper.toDTO(subject.get());
            subjectRepository.deleteById(id);
            return ResponseEntity.ok(subjectDTO);
        }
        else throw new NotFoundException("Subject not found with id: " + id);
    }

    public ResponseEntity<Optional<SubjectDTO>> updateSubject(Long id,SubjectDTO subjectDTO){
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()){
            return ResponseEntity.ok(subjectRepository.findById(id)
            .map(existingSubject -> {
                existingSubject.setSubjectName(subjectDTO.getSubjectName());
                Subject updatedSubject = subjectRepository.save(existingSubject);
                return SubjectMapper.toDTO(updatedSubject);
            }));
        }
        else throw new NotFoundException("Subject not found with id: " + id);
    }
}
