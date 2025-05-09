package co.edu.ufps.segundoparcial1152290.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UnavailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Teacher teacher;

    private String reason;

    private String dayOfWeek;

    private String startTime;
    private String endTime;
}