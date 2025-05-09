package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.AuditTrail;

import java.util.List;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
    List<AuditTrail> findByUserId(Long userId);
}