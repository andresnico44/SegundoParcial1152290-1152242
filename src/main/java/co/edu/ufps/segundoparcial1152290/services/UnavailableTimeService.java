package co.edu.ufps.segundoparcial1152290.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import co.edu.ufps.segundoparcial1152290.entities.UnavailableTime;
import co.edu.ufps.segundoparcial1152290.repositories.UnavailableTimeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnavailableTimeService {

    private final UnavailableTimeRepository unavailableTimeRepository;

    public List<UnavailableTime> findAll() {
        return unavailableTimeRepository.findAll();
    }

    public Optional<UnavailableTime> findById(Long id) {
        return unavailableTimeRepository.findById(id);
    }

    public List<UnavailableTime> findByTeacherId(Long teacherId) {
        return unavailableTimeRepository.findByTeacherId(teacherId);
    }

    public UnavailableTime save(UnavailableTime unavailableTime) {
        return unavailableTimeRepository.save(unavailableTime);
    }

    public void deleteById(Long id) {
        unavailableTimeRepository.deleteById(id);
    }
}