package co.edu.ufps.segundoparcial1152290.services;

import co.edu.ufps.segundoparcial1152290.entities.*;
import co.edu.ufps.segundoparcial1152290.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final GradeHistoryRepository gradeHistoryRepository;
    private final TeacherRepository teacherRepository;

    // Crear evaluación en una asignatura
    public Evaluation createEvaluation(Long subjectId, String title, LocalDate date, double weight) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Evaluation evaluation = Evaluation.builder()
                .title(title)
                .date(date)
                .weight(weight)
                .subject(subject)
                .build();

        return evaluationRepository.save(evaluation);
    }

    // Registrar calificación
    @Transactional
    public Grade gradeStudent(Long evaluationId, Long studentId, double score) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Verificar si el estudiante está inscrito en la asignatura
        boolean isEnrolled = student.getSubjects().stream()
                .anyMatch(enrollment -> enrollment.getSubject().getId().equals(evaluation.getSubject().getId()));
        
        if (!isEnrolled) {
            throw new RuntimeException("Student is not enrolled in this subject");
        }

        Grade grade = Grade.builder()
                .student(student)
                .evaluation(evaluation)
                .score(score)
                .timestamp(LocalDateTime.now())
                .build();

        return gradeRepository.save(grade);
    }

    // Obtener todas las calificaciones de un estudiante
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    // Ver calificaciones por asignatura
    public List<Grade> getGradesBySubject(Long subjectId) {
        // Primero obtenemos todas las evaluaciones de la asignatura
        List<Evaluation> evaluations = evaluationRepository.findBySubjectId(subjectId);
        // Luego obtenemos todas las calificaciones de esas evaluaciones
        return gradeRepository.findByEvaluationIn(evaluations);
    }

    // Actualizar nota y almacenar cambio en GradeHistory
    @Transactional
    public Grade updateGrade(Long gradeId, double newScore, Long teacherId, String reason) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Crear registro histórico antes de actualizar
        GradeHistory history = GradeHistory.builder()
                .grade(grade)
                .oldScore(grade.getScore())
                .newScore(newScore)
                .modifiedBy(teacher)
                .reason(reason)
                .timestamp(LocalDateTime.now())
                .build();

        gradeHistoryRepository.save(history);

        // Actualizar la calificación
        grade.setScore(newScore);
        grade.setTimestamp(LocalDateTime.now());

        return gradeRepository.save(grade);
    }
}