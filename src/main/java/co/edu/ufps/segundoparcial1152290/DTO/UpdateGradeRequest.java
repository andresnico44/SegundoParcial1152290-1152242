package co.edu.ufps.segundoparcial1152290.DTO;

import lombok.Data;

@Data
public class UpdateGradeRequest {
    private double newScore;
    private Long teacherId;
    private String reason;
}
