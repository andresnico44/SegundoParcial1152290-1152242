package co.edu.ufps.segundoparcial1152290.controllers;

import co.edu.ufps.segundoparcial1152290.DTO.GradeHistoryDTO;
import co.edu.ufps.segundoparcial1152290.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeHistoryController {

    private final GradeService gradeService;

    @GetMapping("/{gradeId}/history")
    public ResponseEntity<List<GradeHistoryDTO>> getGradeHistory(@PathVariable Long gradeId) {
        return ResponseEntity.ok(gradeService.getGradeHistory(gradeId));
    }
}