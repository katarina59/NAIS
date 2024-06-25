package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.springframework.http.ResponseEntity;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;

import java.util.List;
import java.util.Optional;

public interface IPsychologistService {
    Psychologist addNewPsychologist(Psychologist psychologist);

    List<Psychologist> findAll();

    Psychologist findById(long id);

    boolean deletePsychologist(long id);

    boolean updatePsychologist(Psychologist psychologist);

    Psychologist recommendMentorWithHighestAverageRating();

    public boolean updateRoleForMentoredStudents(Long psychologistId, String role);
    Optional<Psychologist> recommendMentorByCategory(String category);
}
