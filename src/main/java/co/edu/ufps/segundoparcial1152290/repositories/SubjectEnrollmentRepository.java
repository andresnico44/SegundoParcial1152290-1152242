package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.SubjectEnrollment;

import java.util.List;

public interface SubjectEnrollmentRepository extends JpaRepository<SubjectEnrollment, Long> {
    List<SubjectEnrollment> findByStudentId(Long studentId);
    List<SubjectEnrollment> findBySubjectId(Long subjectId);
}

