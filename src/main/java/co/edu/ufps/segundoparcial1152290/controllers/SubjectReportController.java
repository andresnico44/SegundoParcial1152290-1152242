package co.edu.ufps.segundoparcial1152290.controllers;
import co.edu.ufps.segundoparcial1152290.DTO.EvaluationStats;
import co.edu.ufps.segundoparcial1152290.services.SubjectReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class SubjectReportController {

    private final SubjectReportService subjectReportService;

    @GetMapping("/subject/{subjectId}/performance")
    public ResponseEntity<List<EvaluationStats>> getSubjectPerformanceReport(@PathVariable Long subjectId) {
        List<EvaluationStats> report = subjectReportService.generateSubjectPerformanceReport(subjectId);
        return ResponseEntity.ok(report);
    }
}