package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.SubjectSchedule;

import java.util.List;

public interface SubjectScheduleRepository extends JpaRepository<SubjectSchedule, Long> {
    List<SubjectSchedule> findBySubjectId(Long subjectId);
}