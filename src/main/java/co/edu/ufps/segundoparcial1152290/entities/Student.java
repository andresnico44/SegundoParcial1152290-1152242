package co.edu.ufps.segundoparcial1152290.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Student extends Usuario {

    @OneToMany(mappedBy = "student")
    private List<SubjectEnrollment> subjects;
}