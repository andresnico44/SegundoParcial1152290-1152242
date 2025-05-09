package co.edu.ufps.segundoparcial1152290.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GradeHistoryDTO {
    private double oldScore;
    private double newScore;
    private String modifiedBy; // nombre del docente
    private LocalDateTime timestamp;
    private String reason;
}