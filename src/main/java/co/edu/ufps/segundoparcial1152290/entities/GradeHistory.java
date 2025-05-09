package co.edu.ufps.segundoparcial1152290.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GradeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Grade grade;

    private double oldScore;

    private double newScore;

    @ManyToOne
    private Teacher modifiedBy;

    private String reason;

    private LocalDateTime timestamp;
}