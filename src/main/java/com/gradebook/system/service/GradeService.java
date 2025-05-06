package com.gradebook.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradebook.system.model.Grade;
import com.gradebook.system.model.Student;
import com.gradebook.system.repository.GradeRepository;
import com.gradebook.system.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public Grade assignGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        //Student student = studentRepository.findById(studentId).orElseThrow();
        return gradeRepository.findByStudentId(studentId);
    }

    public Grade updateGrade(Long id, Grade g) {
//        Grade grade = gradeRepository.findById(id).orElseThrow();
//        grade.setScore(updated.getScore());
//        grade.setRemarks(updated.getRemarks());
    	g.setId(id);
        return gradeRepository.save(g);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

	public List<Grade> getGradesByAllStudent() {
		
		return gradeRepository.findAll();
	}
}