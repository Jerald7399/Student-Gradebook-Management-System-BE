package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gradebook.system.DTO.SubjectDTO;
import com.gradebook.system.model.Student;
import com.gradebook.system.model.Subject;
import com.gradebook.system.model.Teacher;
import com.gradebook.system.repository.StudentRepository;
import com.gradebook.system.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
   

    private Teacher getLoggedInTeacher() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return teacherRepository.findByEmail(email).orElseThrow();
    }

    public Student addStudent(Student student) {
        student.setTeacher(getLoggedInTeacher());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findByTeacher(getLoggedInTeacher());
    }

    public Student updateStudent(Long id, Student updated) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(updated.getName());
        student.setRollNo(updated.getRollNo());
        student.setStudentClass(updated.getStudentClass());
        student.setDob(updated.getDob());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

   
}