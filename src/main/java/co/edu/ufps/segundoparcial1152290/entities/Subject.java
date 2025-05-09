package co.edu.ufps.segundoparcial1152290.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<SubjectEnrollment> students;

    @OneToMany(mappedBy = "subject")
    private List<SubjectSchedule> schedules;

    @OneToMany(mappedBy = "subject")
    private List<Evaluation> evaluations;
}