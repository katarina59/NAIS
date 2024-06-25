package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.InternshipProgress;

import java.util.List;

public interface IInternshipProgressService {
    List<InternshipProgress> findAllInternshipProgresss();
    InternshipProgress addNewInternshipProgress(InternshipProgress internshipProgress);
    boolean deleteInternshipProgress(Long id);
    boolean updateInternshipProgress(InternshipProgress internshipProgress);
    InternshipProgress findById(long id);
}