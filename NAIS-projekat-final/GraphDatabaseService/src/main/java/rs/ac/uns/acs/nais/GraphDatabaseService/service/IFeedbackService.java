package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    Feedback addNewFeedback(Feedback feedback);

    List<Feedback> findAll();

    Feedback findById(long id);

    boolean deleteFeedback(long id);

    boolean updateFeedback(Feedback feedback);

    List<Feedback> findAboveThree();
}
