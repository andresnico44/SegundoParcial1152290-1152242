package co.edu.ufps.segundoparcial1152290.DTO;

import lombok.Data;

@Data
public class GradeRequest {
    private Long evaluationId;
    private Long studentId;
    private double score;
}

