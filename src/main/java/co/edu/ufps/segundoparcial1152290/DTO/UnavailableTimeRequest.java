package co.edu.ufps.segundoparcial1152290.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UnavailableTimeRequest {
    private long teacherId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;
}