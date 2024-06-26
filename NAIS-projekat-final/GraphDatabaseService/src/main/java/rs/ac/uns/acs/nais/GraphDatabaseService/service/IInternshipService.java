package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.springframework.http.ResponseEntity;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TopInternshipDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;

import java.util.List;

public interface IInternshipService{
    List<Internship> findAllInternships();
    Internship addNewInternship(Internship internship);
    boolean deleteInternship(Long id);
    boolean updateInternship(Internship internship);
    Internship recommendInternshipsByFaculty(Long studentId);
    List<TopInternshipDTO> findTopInternshipsByCategory();
    List<Internship> findInternshipsByChildCategory();
    List<Internship> recommendInternshipsByRating();
    Internship findById(long id);
}