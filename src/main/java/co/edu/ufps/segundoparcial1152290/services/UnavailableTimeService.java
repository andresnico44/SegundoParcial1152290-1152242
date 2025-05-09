package co.edu.ufps.segundoparcial1152290.services;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.ufps.segundoparcial1152290.entities.UnavailableTime;
import co.edu.ufps.segundoparcial1152290.repositories.UnavailableTimeRepository;

import java.util.List;
import java.util.Optional;
=======

import co.edu.ufps.segundoparcial1152290.entities.*;
import co.edu.ufps.segundoparcial1152290.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
>>>>>>> be02d1948a875395c058ab75c4ed1ca1ffd5283a

@Service
@RequiredArgsConstructor
public class UnavailableTimeService {

    private final UnavailableTimeRepository unavailableTimeRepository;
<<<<<<< HEAD

    public List<UnavailableTime> findAll() {
        return unavailableTimeRepository.findAll();
    }

    public Optional<UnavailableTime> findById(Long id) {
        return unavailableTimeRepository.findById(id);
    }

    public List<UnavailableTime> findByTeacherId(Long teacherId) {
        return unavailableTimeRepository.findByTeacherId(teacherId);
    }

    public UnavailableTime save(UnavailableTime unavailableTime) {
        return unavailableTimeRepository.save(unavailableTime);
    }

    public void deleteById(Long id) {
        unavailableTimeRepository.deleteById(id);
=======
    private final TeacherRepository teacherRepository;

    // Registrar periodos en los que el docente no puede impartir clase
    @Transactional
    public UnavailableTime addUnavailableTime(long teacherId, LocalDateTime startDate, LocalDateTime endDate, String reason) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Validar que la fecha de inicio sea antes que la de fin
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        // Convertir a formato de horas no disponibles diarias
        UnavailableTime unavailableTime = UnavailableTime.builder()
                .teacher(teacher)
                .reason(reason)
                .dayOfWeek(startDate.getDayOfWeek().toString())
                .startTime(startDate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endTime(endDate.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .build();

        return unavailableTimeRepository.save(unavailableTime);
    }

    // Consultar los periodos no disponibles de un docente
    public List<UnavailableTime> getUnavailableTimes(long teacherId) {
        return unavailableTimeRepository.findByTeacherId(teacherId);
    }

    // Eliminar un periodo de no disponibilidad
    @Transactional
    public void deleteUnavailableTime(long unavailableTimeId) {
        unavailableTimeRepository.deleteById(unavailableTimeId);
    }

    // Método adicional para verificar disponibilidad (útil para validaciones)
    public boolean isTeacherAvailable(Long teacherId, LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();
        
        List<UnavailableTime> unavailableTimes = unavailableTimeRepository.findByTeacherId(teacherId);
        
        return unavailableTimes.stream().noneMatch(ut -> 
            DayOfWeek.valueOf(ut.getDayOfWeek()).equals(dayOfWeek) &&
            time.isAfter(LocalTime.parse(ut.getStartTime())) &&
            time.isBefore(LocalTime.parse(ut.getEndTime()))
        );
>>>>>>> be02d1948a875395c058ab75c4ed1ca1ffd5283a
    }
}