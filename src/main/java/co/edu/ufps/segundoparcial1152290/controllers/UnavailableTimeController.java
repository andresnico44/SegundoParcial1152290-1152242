package co.edu.ufps.segundoparcial1152290.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.segundoparcial1152290.entities.UnavailableTime;
import co.edu.ufps.segundoparcial1152290.services.UnavailableTimeService;

import java.util.List;

@RestController
@RequestMapping("/api/tiempos-no-disponibles")
@RequiredArgsConstructor
public class UnavailableTimeController {

    private final UnavailableTimeService unavailableTimeService;

    @GetMapping
    public ResponseEntity<List<UnavailableTime>> getAllUnavailableTimes() {
        return ResponseEntity.ok(unavailableTimeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnavailableTime> getUnavailableTimeById(@PathVariable Long id) {
        return unavailableTimeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/docente/{teacherId}")
    public ResponseEntity<List<UnavailableTime>> getUnavailableTimesByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(unavailableTimeService.findByTeacherId(teacherId));
    }

    @PostMapping
    public ResponseEntity<UnavailableTime> createUnavailableTime(@RequestBody UnavailableTime unavailableTime) {
        return ResponseEntity.ok(unavailableTimeService.save(unavailableTime));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnavailableTime> updateUnavailableTime(@PathVariable Long id, @RequestBody UnavailableTime unavailableTime) {
        return unavailableTimeService.findById(id)
                .map(existing -> {
                    unavailableTime.setId(id);
                    return ResponseEntity.ok(unavailableTimeService.save(unavailableTime));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnavailableTime(@PathVariable Long id) {
        if (unavailableTimeService.findById(id).isPresent()) {
            unavailableTimeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}