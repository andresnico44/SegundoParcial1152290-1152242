package co.edu.ufps.segundoparcial1152290.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import co.edu.ufps.segundoparcial1152290.entities.enums.EnrollmentStatusEnum;
import co.edu.ufps.segundoparcial1152290.entities.enums.RoleEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatusEnum status;

    private LocalDate enrollmentDate;
}


