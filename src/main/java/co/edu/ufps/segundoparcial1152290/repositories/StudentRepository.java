package co.edu.ufps.segundoparcial1152290.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.segundoparcial1152290.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}