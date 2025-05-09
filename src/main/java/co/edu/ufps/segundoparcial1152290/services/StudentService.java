package co.edu.ufps.segundoparcial1152290.services;
import co.edu.ufps.segundoparcial1152290.DTO.StudentReportDTO;
import co.edu.ufps.segundoparcial1152290.entities.*;
import co.edu.ufps.segundoparcial1152290.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectEnrollmentRepository subjectEnrollmentRepository;
    private final EvaluationRepository evaluationRepository;
    private final GradeRepository gradeRepository;

    public StudentReportDTO generateStudentReport(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<SubjectEnrollment> enrollments = subjectEnrollmentRepository.findByStudentId(studentId);

        double total = 0;
        int count = 0;

        List<StudentReportDTO.SubjectSummary> subjects = new ArrayList<>();

        for (SubjectEnrollment enrollment : enrollments) {
            Subject subject = enrollment.getSubject();
            List<Evaluation> evaluations = evaluationRepository.findBySubjectId(subject.getId());

            List<StudentReportDTO.EvaluationSummary> evalSummaries = new ArrayList<>();

            for (Evaluation eval : evaluations) {
                List<Grade> grades = gradeRepository.findByStudentIdAndEvaluationSubjectId(studentId, subject.getId());
                Grade grade = grades.stream()
                        .filter(g -> g.getEvaluation().getId().equals(eval.getId()))
                        .findFirst().orElse(null);

                        if (grade != null) {
                            total += grade.getScore();
                            count++;
                        }

                evalSummaries.add(StudentReportDTO.EvaluationSummary.builder()
                        .evaluationTitle(eval.getTitle())
                        .grade(grade != null ? grade.getScore() : null)
                        .build());
            }

            subjects.add(StudentReportDTO.SubjectSummary.builder()
                    .subjectName(subject.getTitle())
                    .evaluations(evalSummaries)
                    .build());
        }

        double average = count > 0 ? total / count : 0;

        String status = average >= 4.5 ? "SOBRESALIENTE"
                        : average >= 3.0 ? "SATISFACTORIO"
                        : "BAJO";

        return StudentReportDTO.builder()
                .studentId(student.getId())
                .studentName(student.getName())
                .subjects(subjects)
                .averageGrade(average)
                .performanceStatus(status)
                .build();
    }
}
