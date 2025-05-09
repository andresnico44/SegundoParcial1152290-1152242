package co.edu.ufps.segundoparcial1152290.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.segundoparcial1152290.DTO.EnrollmentRequest;
import co.edu.ufps.segundoparcial1152290.DTO.SubjectRequest;
import co.edu.ufps.segundoparcial1152290.entities.Subject;
import co.edu.ufps.segundoparcial1152290.entities.SubjectEnrollment;
import co.edu.ufps.segundoparcial1152290.services.SubjectService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public Subject createSubject(@RequestBody SubjectRequest request) {
        return subjectService.createSubject(request.getTitle(), request.getTeacherId());
    }

    @PostMapping("/enroll")
    public SubjectEnrollment enrollStudent(@RequestBody EnrollmentRequest request) {
        return subjectService.assignStudentToSubject(request.getStudentId(), request.getSubjectId());
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Subject> getByTeacher(@PathVariable Long teacherId) {
        return subjectService.getSubjectsByTeacher(teacherId);
    }

    @GetMapping("/student/{studentId}")
    public List<SubjectEnrollment> getByStudent(@PathVariable Long studentId) {
        return subjectService.getSubjectsByStudent(studentId);
    }

    @DeleteMapping("/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
