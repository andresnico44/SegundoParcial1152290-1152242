package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.UnavailableTime;

import java.util.List;

public interface UnavailableTimeRepository extends JpaRepository<UnavailableTime, Long> {
    List<UnavailableTime> findByTeacherId(Long teacherId);
}