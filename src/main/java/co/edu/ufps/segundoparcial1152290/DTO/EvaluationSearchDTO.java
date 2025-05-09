package co.edu.ufps.segundoparcial1152290.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EvaluationSearchDTO {
    private Long id;
    private String title;
    private String subjectTitle;
    private String teacherName;
    private LocalDate date;
}