package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.GradeHistory;

import java.util.List;

public interface GradeHistoryRepository extends JpaRepository<GradeHistory, Long> {
    List<GradeHistory> findByGradeId(Long gradeId);
}