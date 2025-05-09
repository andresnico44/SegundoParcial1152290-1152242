package co.edu.ufps.segundoparcial1152290.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.ufps.segundoparcial1152290.entities.TeacherSchedule;
import co.edu.ufps.segundoparcial1152290.repositories.TeacherScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherScheduleService {

    private final TeacherScheduleRepository teacherScheduleRepository;

    public List<TeacherSchedule> findAll() {
        return teacherScheduleRepository.findAll();
    }

    public Optional<TeacherSchedule> findById(Long id) {
        return teacherScheduleRepository.findById(id);
    }

    public List<TeacherSchedule> findByTeacherId(Long teacherId) {
        return teacherScheduleRepository.findByTeacherId(teacherId);
    }

    public TeacherSchedule save(TeacherSchedule schedule) {
        return teacherScheduleRepository.save(schedule);
    }

    public void deleteById(Long id) {
        teacherScheduleRepository.deleteById(id);
    }
}