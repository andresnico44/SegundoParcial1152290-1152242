package co.edu.ufps.segundoparcial1152290.services;

import co.edu.ufps.segundoparcial1152290.DTO.GradeHistoryDTO;
import co.edu.ufps.segundoparcial1152290.entities.Grade;
import co.edu.ufps.segundoparcial1152290.entities.GradeHistory;
import co.edu.ufps.segundoparcial1152290.repositories.GradeHistoryRepository;
import co.edu.ufps.segundoparcial1152290.repositories.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeHistoryRepository gradeHistoryRepository;

    public List<GradeHistoryDTO> getGradeHistory(Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        List<GradeHistory> history = gradeHistoryRepository.findByGradeId(grade.getId());

        return history.stream().map(record -> new GradeHistoryDTO(
                record.getOldScore(),
                record.getNewScore(),
                record.getModifiedBy().getName(),
                record.getTimestamp(),
                record.getReason()
        )).collect(Collectors.toList());
    }
}