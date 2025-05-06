package com.gradebook.system.service;



import com.gradebook.system.DTO.ReportCardDTO;
import com.gradebook.system.model.Grade;
import com.gradebook.system.model.Student;
import com.gradebook.system.repository.GradeRepository;
import com.gradebook.system.repository.StudentRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReportService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public ReportCardDTO generateReportCard(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        double avg = grades.stream().mapToDouble(Grade::getScore).average().orElse(0);
        double max = grades.stream().mapToDouble(Grade::getScore).max().orElse(0);
        double min = grades.stream().mapToDouble(Grade::getScore).min().orElse(0);

        List<ReportCardDTO.SubjectGradeDTO> subjectGrades = grades.stream()
                .map(g -> ReportCardDTO.SubjectGradeDTO.builder()
                        .subject(g.getSubject().getName())
                        .score(g.getScore())
                        .grade(g.getGrade())
                        .build())
                .collect(Collectors.toList());

        return ReportCardDTO.builder()
        		.studentId(student.getId())
                .studentName(student.getName())
                .rollNo(student.getRollNo())
                .studentClass(student.getStudentClass())
                .average(avg)
                .highest(max)
                .lowest(min)
                .subjectGrades(subjectGrades)
                .build();

          
    }
    public ByteArrayInputStream generateReport(Long studentId) throws IOException {
    	Student student = studentRepository.findById(studentId).orElseThrow();
    	List<Grade> grades = gradeRepository.findByStudentId(studentId);
    	
    	Document doc = new Document();
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	 
    	try {
    	PdfWriter.getInstance(doc, out);
    	doc.open();
    	doc.add(new Paragraph("Student Id 		\t: " +student.getId()));
    	doc.add(new Paragraph("Report Card for 	\t: " + student.getName()));
    	doc.add(new Paragraph("Roll No 			\t: " + student.getRollNo()));
    	doc.add(new Paragraph("Class 			\t: " + student.getStudentClass()));
    	doc.add(new Paragraph("Date of Birth 	\t: " + student.getDob().toString()));
    	doc.add(new Paragraph(" ")); // Blank line
    	 
    	PdfPTable table = new PdfPTable(3);
    	table.addCell("Subject");
    	table.addCell("Score");
    	table.addCell("Grade");
    	
    
    	for (Grade g : grades) {
    		
    	table.addCell(g.getSubject().getName());
    	table.addCell(String.valueOf(g.getScore()));
    	table.addCell(g.getGrade());
    	}
    	 
    	doc.add(table);
    	} catch (DocumentException e) {
    	throw new IOException(e);
    	} finally {
    	doc.close();
    	}
    	 
    	return new ByteArrayInputStream(out.toByteArray());
    	}
	
	          
    public List<ReportCardDTO> getAllReport() {
        List<Student> students = studentRepository.findAll();
        List<Grade> allGrades = gradeRepository.findAll();

        List<ReportCardDTO> reportCards = new ArrayList<>();

        for (Student student : students) {
            List<Grade> studentGrades = allGrades.stream()
                    .filter(g -> g.getStudent().getId().equals(student.getId()))
                    .collect(Collectors.toList());

            double avg = studentGrades.stream().mapToDouble(Grade::getScore).average().orElse(0);
            double max = studentGrades.stream().mapToDouble(Grade::getScore).max().orElse(0);
            double min = studentGrades.stream().mapToDouble(Grade::getScore).min().orElse(0);

            List<ReportCardDTO.SubjectGradeDTO> subjectGrades = studentGrades.stream()
                    .map(g -> ReportCardDTO.SubjectGradeDTO.builder()
                            .subject(g.getSubject().getName())
                            .score(g.getScore())
                            .grade(g.getGrade())
                            .build())
                    .collect(Collectors.toList());

            ReportCardDTO reportCard = ReportCardDTO.builder()
            		.studentId(student.getId())
                    .studentName(student.getName())
                    .rollNo(student.getRollNo())
                    .studentClass(student.getStudentClass())
                    .average(avg)
                    .highest(max)
                    .lowest(min)
                    .subjectGrades(subjectGrades)
                    .build();

            reportCards.add(reportCard);
        }

        return reportCards;
    }
    	
}
