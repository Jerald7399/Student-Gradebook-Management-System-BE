package com.gradebook.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradebook.system.model.Student;
import com.gradebook.system.model.Teacher;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByTeacher(Teacher teacher);
}

