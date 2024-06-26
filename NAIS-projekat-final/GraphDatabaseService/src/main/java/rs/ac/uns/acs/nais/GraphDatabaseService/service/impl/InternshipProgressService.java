package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.InternshipProgress;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.InternshipProgressRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.InternshipRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IInternshipProgressService;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipProgressService implements IInternshipProgressService {

    public final InternshipProgressRepository internshipProgressRepository;

    public InternshipProgressService(InternshipProgressRepository internshipProgressRepository) {
        this.internshipProgressRepository = internshipProgressRepository;
    }
    @Override
    public List<InternshipProgress> findAllInternshipProgresss() {
        return internshipProgressRepository.findAll();
    }

    @Override
    public InternshipProgress addNewInternshipProgress(InternshipProgress internshipProgress) {
        InternshipProgress internshipProgress1 = internshipProgressRepository.save(internshipProgress);

        return internshipProgress1;
    }

    @Override
    public boolean deleteInternshipProgress(Long id) {
        Optional<InternshipProgress> internshipProgress = internshipProgressRepository.findById(id);
        if(internshipProgress.isPresent()){
            internshipProgressRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateInternshipProgress(InternshipProgress internshipProgress) {
        Optional<InternshipProgress> internshipProgress1 = internshipProgressRepository.findById(internshipProgress.getId());
        if(internshipProgress1.isPresent()){
            internshipProgress1.get().setStartDate(internshipProgress.getStartDate());
            internshipProgress1.get().setEndDate(internshipProgress.getEndDate());
            internshipProgress1.get().setTaskDescription(internshipProgress.getTaskDescription());
            internshipProgressRepository.save(internshipProgress1.get());
            return true;
        }
        return false;
    }

    @Override
    public InternshipProgress findById(long id) {
        return internshipProgressRepository.findById(id).orElse(null);
    }
}