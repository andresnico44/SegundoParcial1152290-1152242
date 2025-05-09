package co.edu.ufps.segundoparcial1152290.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.segundoparcial1152290.entities.SubjectSchedule;
import co.edu.ufps.segundoparcial1152290.services.SubjectScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/horarios-materia")
@RequiredArgsConstructor
public class SubjectScheduleController {

    private final SubjectScheduleService subjectScheduleService;

    @GetMapping
    public ResponseEntity<List<SubjectSchedule>> getAllSubjectSchedules() {
        return ResponseEntity.ok(subjectScheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectSchedule> getSubjectScheduleById(@PathVariable Long id) {
        return subjectScheduleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/materia/{subjectId}")
    public ResponseEntity<List<SubjectSchedule>> getSubjectSchedulesBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectScheduleService.findByCourseId(subjectId));
    }

    @PostMapping
    public ResponseEntity<SubjectSchedule> createSubjectSchedule(@RequestBody SubjectSchedule subjectSchedule) {
        return ResponseEntity.ok(subjectScheduleService.save(subjectSchedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectSchedule> updateSubjectSchedule(@PathVariable Long id, @RequestBody SubjectSchedule subjectSchedule) {
        return subjectScheduleService.findById(id)
                .map(existing -> {
                    subjectSchedule.setId(id);
                    return ResponseEntity.ok(subjectScheduleService.save(subjectSchedule));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectSchedule(@PathVariable Long id) {
        if (subjectScheduleService.findById(id).isPresent()) {
            subjectScheduleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}