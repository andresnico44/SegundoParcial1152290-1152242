package co.edu.ufps.segundoparcial1152290.repositories;

import co.edu.ufps.segundoparcial1152290.entities.TeacherSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface TeacherScheduleRepository extends JpaRepository<TeacherSchedule, Long> {
    List<TeacherSchedule> findByTeacherId(Long teacherId);
    List<TeacherSchedule> findByTeacherIdAndDayOfWeek(Long teacherId, DayOfWeek day);
}