package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.FeedbackRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PsychologistRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.StudentRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPsychologistService;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologistService implements IPsychologistService {

    public final PsychologistRepository psychologistRepository;
    public final StudentRepository studentRepository;

    public PsychologistService(PsychologistRepository psychologistRepository, StudentRepository studentRepository) {
        this.psychologistRepository = psychologistRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Psychologist addNewPsychologist(Psychologist psychologist) {
        return psychologistRepository.save(psychologist);
    }

    @Override
    public List<Psychologist> findAll() {
        return psychologistRepository.findAll();
    }

    @Override
    public Psychologist findById(long id) {
        return psychologistRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deletePsychologist(long id) {
        Optional<Psychologist> psychologist = psychologistRepository.findById(id);
        if(psychologist.isPresent()){
            psychologistRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePsychologist(Psychologist psychologist) {
        Optional<Psychologist> psychologist1 = psychologistRepository.findById(psychologist.getId());
        if(psychologist1.isPresent()){
            psychologist1.get().setName(psychologist.getName());
            psychologist1.get().setLastName(psychologist.getLastName());
            psychologist1.get().setEmail(psychologist.getEmail());
            psychologist1.get().setPassword(psychologist.getPassword());
            psychologist1.get().setUsername(psychologist.getUsername());
            psychologistRepository.save(psychologist1.get());
            return true;
        }
        return false;
    }

    @Override
    public Psychologist recommendMentorWithHighestAverageRating() {
        return psychologistRepository.recommendMentorWithHighestAverageRating();
    }

    @Override
    public boolean updateRoleForMentoredStudents(Long psychologistId, String role) {
        Optional<Psychologist> psychologistOptional = psychologistRepository.findById(psychologistId);
        if (psychologistOptional.isEmpty()) {
            return false; // Ako psiholog ne postoji, ne možemo nastaviti
        }

        List<InternshipProgress> psychologistProgresses = psychologistOptional.get().getInternshipProgress();
        List<Student> students = studentRepository.findAll();
        boolean updated = false;

        for (Student student : students) {
            List<HasProgress> studentProgresses = student.getProgress();

            for (HasProgress studentHasProgress : studentProgresses) {
                for (InternshipProgress psychologistProgress : psychologistProgresses) {
                    if (studentHasProgress.getInternshipProgress().getId().equals(psychologistProgress.getId())) {
                        studentHasProgress.setRoles(role);
                        updated = true;
                    }
                }
            }

            if (updated) {
                studentRepository.save(student);
                updated = false;
            }
        }
        return true;
    }

    public Optional<Psychologist> recommendMentorByCategory(String category) {
        return psychologistRepository.findTopMentorByInternshipCategory(category);
    }
}
