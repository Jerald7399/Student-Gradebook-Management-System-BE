package com.gradebook.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradebook.system.model.Grade;
import com.gradebook.system.service.GradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<Grade> assignGrade(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.assignGrade(grade));
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getGradesByAllStudent() {
        return ResponseEntity.ok(gradeService.getGradesByAllStudent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getGradesByStudentId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}