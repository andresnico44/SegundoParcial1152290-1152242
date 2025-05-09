package co.edu.ufps.segundoparcial1152290.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EvaluationRequest {
    private Long subjectId;
    private String title;
    private LocalDate date;
    private double weight;
}
