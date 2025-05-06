package com.gradebook.system.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportCardDTO {
	
	private Long studentId;
    private String studentName;
    private String rollNo;
    private String studentClass;
    private Double average;
    private Double highest;
    private Double lowest;
    private List<SubjectGradeDTO> subjectGrades;
    
    

    @Data
    @Builder   
public static class SubjectGradeDTO {
        private String subject;
        private Double score;
        private String grade;
       
    }
}
