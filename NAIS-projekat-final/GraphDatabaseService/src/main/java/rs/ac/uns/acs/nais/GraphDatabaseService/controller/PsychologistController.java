package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Customer;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Feedback;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.FeedbackService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PsychologistService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/psychologists.json")
public class PsychologistController {

    @Autowired
    private final PsychologistService psychologistService;

    public PsychologistController(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    @PostMapping
    public ResponseEntity<Psychologist> addNewFeedback(@RequestBody Psychologist psychologist){
        Psychologist createdPsychologist = psychologistService.addNewPsychologist(psychologist);
        return new ResponseEntity<>(createdPsychologist, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Psychologist>> findAll() {
        List<Psychologist> psychologists = psychologistService.findAll();
        return new ResponseEntity<>(psychologists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psychologist> findById(@PathVariable long id) {
        Psychologist psychologist = psychologistService.findById(id);
        if (psychologist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(psychologist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Psychologist> deletePsychologist(@PathVariable long id){
        if(psychologistService.deletePsychologist(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Psychologist> updatePsychologist(@RequestBody Psychologist psychologist){
        if(psychologistService.updatePsychologist(psychologist)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recommend")
    public ResponseEntity<Psychologist> recommendMentorWithHighestAverageRating(){
        Psychologist psychologist = psychologistService.recommendMentorWithHighestAverageRating();
        if (psychologist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(psychologist, HttpStatus.OK);
    }

    @PutMapping("/{psychologistId}/updateRole")
    public ResponseEntity<Void> updateStudentRole(@PathVariable Long psychologistId, @RequestParam String role) {
        boolean updated = psychologistService.updateRoleForMentoredStudents(psychologistId, role);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recommend-mentor")
    public ResponseEntity<Psychologist> recommendMentorByCategory(@RequestParam String category) {
        Optional<Psychologist> recommendedMentor = psychologistService.recommendMentorByCategory(category);
        return recommendedMentor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
