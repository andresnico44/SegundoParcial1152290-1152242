package co.edu.ufps.segundoparcial1152290.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.ufps.segundoparcial1152290.entities.SubjectSchedule;
import co.edu.ufps.segundoparcial1152290.repositories.SubjectScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectScheduleService {

    private final SubjectScheduleRepository subjectScheduleRepository;

    public List<SubjectSchedule> findAll() {
        return subjectScheduleRepository.findAll();
    }

    public Optional<SubjectSchedule> findById(Long id) {
        return subjectScheduleRepository.findById(id);
    }

    public List<SubjectSchedule> findByCourseId(Long courseId) {
        return subjectScheduleRepository.findBySubjectId(courseId);
    }

    public SubjectSchedule save(SubjectSchedule subjectSchedule) {
        return subjectScheduleRepository.save(subjectSchedule);
    }

    public void deleteById(Long id) {
        subjectScheduleRepository.deleteById(id);
    }
}