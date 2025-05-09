package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.Evaluation;
import co.edu.ufps.segundoparcial1152290.entities.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByEvaluationId(Long evaluationId);
    List<Grade> findByEvaluationIn(List<Evaluation> evaluations);
    List<Grade> findByStudentIdAndEvaluationSubjectId(Long studentId, Long subjectId);
}