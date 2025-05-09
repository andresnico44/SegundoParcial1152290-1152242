package co.edu.ufps.segundoparcial1152290.services;

import co.edu.ufps.segundoparcial1152290.DTO.EvaluationStats;
import co.edu.ufps.segundoparcial1152290.entities.Evaluation;
import co.edu.ufps.segundoparcial1152290.entities.Grade;
import co.edu.ufps.segundoparcial1152290.repositories.EvaluationRepository;
import co.edu.ufps.segundoparcial1152290.repositories.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectReportService {

    private final EvaluationRepository evaluationRepository;
    private final GradeRepository gradeRepository;

    public List<EvaluationStats> generateSubjectPerformanceReport(Long subjectId) {
        List<Evaluation> evaluations = evaluationRepository.findBySubjectId(subjectId);
        List<EvaluationStats> statsList = new ArrayList<>();

        for (Evaluation evaluation : evaluations) {
            List<Grade> grades = gradeRepository.findByEvaluationId(evaluation.getId());

            if (grades.isEmpty()) continue;

            double average = grades.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
            double stdDev = calculateStandardDeviation(grades, average);
            long passed = grades.stream().filter(g -> g.getScore() >= 3.0).count();
            long failed = grades.size() - passed;

            EvaluationStats stats = new EvaluationStats();
            stats.setEvaluationTitle(evaluation.getTitle());
            stats.setAverage(average);
            stats.setStandardDeviation(stdDev);
            stats.setPassed(passed);
            stats.setFailed(failed);

            statsList.add(stats);
        }

        return statsList;
    }

    private double calculateStandardDeviation(List<Grade> grades, double mean) {
        double variance = grades.stream()
            .mapToDouble(g -> Math.pow(g.getScore() - mean, 2))
            .average()
            .orElse(0.0);
        return Math.sqrt(variance);
    }
}
