package co.edu.ufps.segundoparcial1152290.controller;

import co.edu.ufps.segundoparcial1152290.entities.UnavailableTime;
import co.edu.ufps.segundoparcial1152290.services.UnavailableTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/unavailable-times")
@RequiredArgsConstructor
public class UnavailableTimeController {

    private final UnavailableTimeService unavailableTimeService;

    @PostMapping
    public UnavailableTime addUnavailableTime(
            @RequestParam long teacherId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam String reason) {
        return unavailableTimeService.addUnavailableTime(teacherId, startDate, endDate, reason);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<UnavailableTime> getUnavailableTimes(@PathVariable long teacherId) {
        return unavailableTimeService.getUnavailableTimes(teacherId);
    }

    @DeleteMapping("/{unavailableTimeId}")
    public void deleteUnavailableTime(@PathVariable long unavailableTimeId) {
        unavailableTimeService.deleteUnavailableTime(unavailableTimeId);
    }
}