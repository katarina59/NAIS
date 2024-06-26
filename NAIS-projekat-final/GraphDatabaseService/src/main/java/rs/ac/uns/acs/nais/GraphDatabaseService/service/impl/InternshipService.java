package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TopInternshipDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.InternshipRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IInternshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InternshipService implements IInternshipService {

    public final InternshipRepository internshipRepository;

    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @Override
    public List<Internship> findAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public Internship addNewInternship(Internship internship) {
        Internship internship1 = internshipRepository.save(internship);

        return internship1;
    }

    @Override
    public boolean deleteInternship(Long id) {
        Optional<Internship> internship = internshipRepository.findById(id);
        if(internship.isPresent()){
            internshipRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateInternship(Internship internship) {
        Optional<Internship> internship1 = internshipRepository.findById(internship.getId());
        if(internship1.isPresent()){
            internship1.get().setCategory(internship.getCategory());
            internship1.get().setTitle(internship.getTitle());
            internshipRepository.save(internship1.get());
            return true;
        }
        return false;
    }

    @Override
    public Internship recommendInternshipsByFaculty(Long studentId) {
        return internshipRepository.recommendInternshipByFaculty(studentId);
    }

    @Override
    public List<TopInternshipDTO> findTopInternshipsByCategory() {
        List<Internship> internships = internshipRepository.findTopInternshipsByCategory();
        List<TopInternshipDTO> dtos = new ArrayList<>();
        for (Internship intern:internships) {
            TopInternshipDTO dto = new TopInternshipDTO(intern.getCategory(), intern.getTitle());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<Internship> findInternshipsByChildCategory() {
        return internshipRepository.findInternshipsByChildAndAdolescentPsycholog();
    }

    @Override
    public Internship findById(long id) {
        return internshipRepository.findById(id).orElse(null);
    }

    public List<Internship> recommendInternshipsByRating() {
        return internshipRepository.recommendInternshipsByRating();
    }
}