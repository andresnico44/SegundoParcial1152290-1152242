package co.edu.ufps.segundoparcial1152290.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Evaluation evaluation;

    private double score;

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeHistory> history;
}