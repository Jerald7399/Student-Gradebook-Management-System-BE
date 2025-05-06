package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gradebook.system.DTO.SubjectDTO;
import com.gradebook.system.model.Subject;
import com.gradebook.system.model.Teacher;
import com.gradebook.system.repository.SubjectRepository;
import com.gradebook.system.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    private Teacher getLoggedInTeacher() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return teacherRepository.findByEmail(email).orElseThrow();
    }

    public Subject addSubject(Subject subject) {
        subject.setTeacher(getLoggedInTeacher());
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        Teacher teacher = getLoggedInTeacher(); // ✅ Use the correct method
        return subjectRepository.findByTeacherId(teacher.getId());
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    public Subject updateStudent(Long id, SubjectDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("Subject ID must not be null");
        }
        if (dto.getTeacher() == null || dto.getTeacher().getId() == null) {
            throw new IllegalArgumentException("Teacher ID must not be null");
        }

        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subject not found"));

        Teacher teacher = teacherRepository.findById(dto.getTeacher().getId())
        	    .orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        subject.setName(dto.getName());
        subject.setTeacher(teacher);

        return subjectRepository.save(subject);
    }

}