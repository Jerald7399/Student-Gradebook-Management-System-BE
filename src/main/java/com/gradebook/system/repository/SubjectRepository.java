package com.gradebook.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradebook.system.model.Subject;
import com.gradebook.system.model.Teacher;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
	List<Subject> findByTeacherId(Long teacherId);
}