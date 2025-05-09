package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.TeacherSchedule;

import java.util.List;

public interface TeacherScheduleRepository extends JpaRepository<TeacherSchedule, Long> {
    List<TeacherSchedule> findByTeacherId(Long teacherId);
}