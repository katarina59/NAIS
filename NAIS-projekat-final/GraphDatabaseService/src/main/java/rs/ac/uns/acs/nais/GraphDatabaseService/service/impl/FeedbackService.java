package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Feedback;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Product;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.FeedbackRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IFeedbackService;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {
    public final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback addNewFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteFeedback(long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if(feedback.isPresent()){
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        Optional<Feedback> feedback1 = feedbackRepository.findById(feedback.getId());
        if(feedback1.isPresent()){
            feedback1.get().setComment(feedback.getComment());
            feedback1.get().setRating(feedback.getRating());
            return true;
        }
        return false;
    }

    @Override
    public List<Feedback> findAboveThree() {
        return feedbackRepository.findAllAboveThree();
    }
}
