package co.edu.ufps.segundoparcial1152290.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import co.edu.ufps.segundoparcial1152290.entities.SubjectEnrollment;

public interface SubjectEnrollmentRepository extends JpaRepository<SubjectEnrollment, Long> {
    List<SubjectEnrollment> findByStudentId(Long studentId);
    boolean existsByStudentIdAndSubjectId(Long studentId, Long subjectId);
    
    @Transactional
    @Modifying
    void deleteBySubjectId(Long subjectId);
}