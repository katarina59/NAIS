package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Customer;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Feedback;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/feedbacks.json")
public class FeedbackController {

    @Autowired
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Feedback> addNewFeedback(@RequestBody Feedback feedback){
        Feedback createdFeedback = feedbackService.addNewFeedback(feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Feedback>> findAll() {
        List<Feedback> feedbacks = feedbackService.findAll();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> findById(@PathVariable long id) {
        Feedback feedback = feedbackService.findById(id);
        if (feedback == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable long id){
        if(feedbackService.deleteFeedback(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback){
        if(feedbackService.updateFeedback(feedback)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/above-three")
    public ResponseEntity<List<Feedback>> findAboveThree() {
        List<Feedback> feedbacks = feedbackService.findAboveThree();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
