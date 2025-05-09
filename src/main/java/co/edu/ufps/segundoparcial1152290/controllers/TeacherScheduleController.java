package co.edu.ufps.segundoparcial1152290.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.segundoparcial1152290.entities.TeacherSchedule;
import co.edu.ufps.segundoparcial1152290.services.TeacherScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/horarios-docente")
@RequiredArgsConstructor
public class TeacherScheduleController {

    private final TeacherScheduleService teacherScheduleService;

    @GetMapping
    public ResponseEntity<List<TeacherSchedule>> getAllSchedules() {
        return ResponseEntity.ok(teacherScheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherSchedule> getScheduleById(@PathVariable Long id) {
        return teacherScheduleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/docente/{teacherId}")
    public ResponseEntity<List<TeacherSchedule>> getSchedulesByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherScheduleService.findByTeacherId(teacherId));
    }

    @PostMapping
    public ResponseEntity<TeacherSchedule> createSchedule(@RequestBody TeacherSchedule schedule) {
        return ResponseEntity.ok(teacherScheduleService.save(schedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherSchedule> updateSchedule(@PathVariable Long id, @RequestBody TeacherSchedule schedule) {
        return teacherScheduleService.findById(id)
                .map(existing -> {
                    schedule.setId(id);
                    return ResponseEntity.ok(teacherScheduleService.save(schedule));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (teacherScheduleService.findById(id).isPresent()) {
            teacherScheduleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}