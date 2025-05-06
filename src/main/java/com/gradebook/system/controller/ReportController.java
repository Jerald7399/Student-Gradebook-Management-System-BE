package com.gradebook.system.controller;




import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradebook.system.DTO.ReportCardDTO;
import com.gradebook.system.service.ReportService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportCardDTO>> getAllReport() {
        return ResponseEntity.ok(reportService.getAllReport());
    }
    
    @GetMapping("/{studentId}")
    public ResponseEntity<ReportCardDTO> getReport(@PathVariable Long studentId) {
        return ResponseEntity.ok(reportService.generateReportCard(studentId));
    
        
    }
    
    @GetMapping("/{studentId}/export")
    public ResponseEntity<InputStreamResource> export(@PathVariable Long studentId) throws IOException {
    ByteArrayInputStream stream = reportService.generateReport(studentId);
     
    org.springframework.http.HttpHeaders headers =new org.springframework.http.HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=report.pdf");
     
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(stream));
    }
}
