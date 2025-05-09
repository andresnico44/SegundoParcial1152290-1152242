package co.edu.ufps.segundoparcial1152290.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Teacher extends Usuario {

    @OneToMany(mappedBy = "teacher")
    private List<TeacherSchedule> availableSchedules;

    @OneToMany(mappedBy = "teacher")
    private List<UnavailableTime> unavailableTimes;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;
}