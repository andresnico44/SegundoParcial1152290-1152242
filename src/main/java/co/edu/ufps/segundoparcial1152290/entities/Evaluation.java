package co.edu.ufps.segundoparcial1152290.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate date;

    private double weight; // Porcentaje de la nota final

    @ManyToOne
    private Subject subject;
}