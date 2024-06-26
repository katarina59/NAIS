package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.InternshipProgress;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.InternshipProgressService;

import java.util.List;

@RestController
@RequestMapping("/internship_progresses.json")
public class InternshipProgressController {
    @Autowired
    private final InternshipProgressService internshipProgressService;


    public InternshipProgressController(InternshipProgressService internshipProgressService) {
        this.internshipProgressService = internshipProgressService;
    }

    @GetMapping
    public ResponseEntity<List<InternshipProgress>> getAllInternshipProgress() {
        List<InternshipProgress> internshipProgresses = internshipProgressService.findAllInternshipProgresss();
        return new ResponseEntity<>(internshipProgresses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InternshipProgress> addNewInternshipProgress(@RequestBody InternshipProgress internshipProgress){
        InternshipProgress createdInternshipProgress = internshipProgressService.addNewInternshipProgress(internshipProgress);
        return new ResponseEntity<>(createdInternshipProgress, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InternshipProgress> deleteInternshipProgress(@PathVariable("id") Long id){
        if(internshipProgressService.deleteInternshipProgress(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<InternshipProgress> updateInternshipProgress(@RequestBody InternshipProgress internshipProgress){
        if(internshipProgressService.updateInternshipProgress(internshipProgress)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipProgress> findById(@PathVariable long id) {
        InternshipProgress internshipProgress = internshipProgressService.findById(id);
        if (internshipProgress == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(internshipProgress, HttpStatus.OK);
    }
}