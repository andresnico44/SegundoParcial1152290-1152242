package co.edu.ufps.segundoparcial1152290.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationStats {
    private String evaluationTitle;
    private double average;
    private double standardDeviation;
    private long passed;
    private long failed;
}