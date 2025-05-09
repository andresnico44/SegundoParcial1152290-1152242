package co.edu.ufps.segundoparcial1152290.services;

import co.edu.ufps.segundoparcial1152290.entities.*;
import co.edu.ufps.segundoparcial1152290.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectEnrollmentRepository subjectEnrollmentRepository;

    // Registro de una asignatura asociada a un docente
    public Subject createSubject(String title, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        Subject subject = Subject.builder()
                .title(title)
                .teacher(teacher)
                .build();
        
        return subjectRepository.save(subject);
    }

    // Inscripción de un estudiante a una asignatura
    @Transactional
    public SubjectEnrollment assignStudentToSubject(Long studentId, Long subjectId) {
        if (subjectEnrollmentRepository.existsByStudentIdAndSubjectId(studentId, subjectId)) {
            throw new RuntimeException("Student is already enrolled in this subject");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        SubjectEnrollment enrollment = SubjectEnrollment.builder()
                .student(student)
                .subject(subject)
                .build();

        return subjectEnrollmentRepository.save(enrollment);
    }

    // Listar asignaturas de un docente
    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    // Listar asignaturas inscritas por un estudiante
    public List<SubjectEnrollment> getSubjectsByStudent(Long studentId) {
        return subjectEnrollmentRepository.findByStudentId(studentId);
    }

    // Eliminar asignatura (con cascada controlada)
    @Transactional
    public void deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        
        // Primero eliminamos las inscripciones de estudiantes
        subjectEnrollmentRepository.deleteBySubjectId(subjectId);
        
        // Luego eliminamos la asignatura
        subjectRepository.delete(subject);
    }
}