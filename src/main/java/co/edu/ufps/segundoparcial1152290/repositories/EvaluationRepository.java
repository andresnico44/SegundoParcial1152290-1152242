package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ufps.segundoparcial1152290.entities.Evaluation;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findBySubjectId(Long subjectId);

    @Query("SELECT e FROM Evaluation e " +
           "JOIN e.subject s " +
           "JOIN s.teacher t " +
           "WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Evaluation> searchByKeyword(@Param("keyword") String keyword);
}