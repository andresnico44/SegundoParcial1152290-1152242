package co.edu.ufps.segundoparcial1152290.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StudentReportDTO {

    private Long studentId;
    private String studentName;

    private List<SubjectSummary> subjects;
    private double averageGrade;
    private String performanceStatus;

    @Data
    @AllArgsConstructor
    @Builder
    public static class SubjectSummary {
        private String subjectName;
        private List<EvaluationSummary> evaluations;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class EvaluationSummary {
        private String evaluationTitle;
        private Double grade;
    }
}