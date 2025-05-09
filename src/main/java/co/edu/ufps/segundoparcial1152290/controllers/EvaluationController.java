package co.edu.ufps.segundoparcial1152290.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.segundoparcial1152290.DTO.EvaluationRequest;
import co.edu.ufps.segundoparcial1152290.DTO.EvaluationSearchDTO;
import co.edu.ufps.segundoparcial1152290.DTO.GradeRequest;
import co.edu.ufps.segundoparcial1152290.DTO.UpdateGradeRequest;
import co.edu.ufps.segundoparcial1152290.entities.Evaluation;
import co.edu.ufps.segundoparcial1152290.entities.Grade;
import co.edu.ufps.segundoparcial1152290.services.EvaluationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public Evaluation createEvaluation(@RequestBody EvaluationRequest request) {
        return evaluationService.createEvaluation(
            request.getSubjectId(), 
            request.getTitle(), 
            request.getDate(),
            request.getWeight()
        );
    }

    @PostMapping("/grade")
    public Grade gradeStudent(@RequestBody GradeRequest request) {
        return evaluationService.gradeStudent(
            request.getEvaluationId(),
            request.getStudentId(),
            request.getScore()
        );
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return evaluationService.getGradesByStudent(studentId);
    }

    @GetMapping("/subject/{subjectId}")
    public List<Grade> getGradesBySubject(@PathVariable Long subjectId) {
        return evaluationService.getGradesBySubject(subjectId);
    }

    @PutMapping("/grade/{gradeId}")
    public Grade updateGrade(
            @PathVariable Long gradeId,
            @RequestBody UpdateGradeRequest request) {
        return evaluationService.updateGrade(
            gradeId,
            request.getNewScore(),
            request.getTeacherId(),
            request.getReason()
        );
    }

    @GetMapping("/search")
public ResponseEntity<List<EvaluationSearchDTO>> searchEvaluations(
        @RequestParam("keyword") String keyword) {
    return ResponseEntity.ok(evaluationService.searchEvaluations(keyword));
}
}