package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.Status;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.StudentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students.json")
public class StudentController {

    @Autowired
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
        Student createdStudent = studentService.addNewStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id){
        if(studentService.deleteStudent(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        if(studentService.updateStudent(student)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("review/{internshipId}")
    public void updateStatus(@PathVariable Long internshipId){
        studentService.updateStatus(internshipId);
    }

    @PutMapping("set-today/{studentId}")
    public void updateDate(@PathVariable Long studentId){
        studentService.updateDate(studentId);
    }

    @GetMapping("/above-avarage/{internshipId}")
    public ResponseEntity<List<Student>> recommendStudentsWithBestAvrageGrade(@PathVariable Long internshipId){
        List<Student> students = studentService.recommendStudentsWithBestAvrageGrade(internshipId);
        if (students == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{studentId}/recommend-role")
    public ResponseEntity<String> recommendRoleForStudent(@PathVariable Long studentId) {
        String recommendedRole = studentService.findMostCommonRoleByStudentId(studentId);
        return ResponseEntity.ok(recommendedRole);
    }
    @PutMapping("/updateRolesForSpecializedTherapies")
    public ResponseEntity<Void> updateRolesForSpecializedTherapies() {
        studentService.updateRolesForSpecializedTherapies();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{studentId}/recommendMentor")
    public ResponseEntity<Psychologist> recommendMentorByInternshipProgress(@PathVariable Long studentId) {
        Psychologist recommendedMentor = studentService.recommendMentorByInternshipProgress(studentId);
        if (recommendedMentor != null) {
            return new ResponseEntity<>(recommendedMentor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/{studentId}/apply/{internshipId}")
    public ResponseEntity<Student> applyForInternship(
            @PathVariable Long studentId,
            @PathVariable Long internshipId,
            @RequestParam(required = false) LocalDate applicationDate,
            @RequestParam(required = false) Status status) {
        Student updatedStudent = studentService.applyForInternship(studentId, internshipId, applicationDate, status);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}/applications")
    public ResponseEntity<List<AppliesFor>> getAllInternshipApplications(@PathVariable Long studentId) {
        List<AppliesFor> applications = studentService.getAllInternshipApplications(studentId);
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{studentId}/update-application/{internshipId}")
    public ResponseEntity<Student> updateInternshipApplication(
            @PathVariable Long studentId,
            @PathVariable Long internshipId,
            @RequestParam(required = false) LocalDate applicationDate,
            @RequestParam(required = false) Status status) {
        Student updatedStudent = studentService.updateInternshipApplication(studentId, internshipId, applicationDate, status);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}/delete-application/{internshipId}")
    public ResponseEntity<Student> deleteInternshipApplication(
            @PathVariable Long studentId,
            @PathVariable Long internshipId) {
        Student updatedStudent = studentService.deleteInternshipApplication(studentId, internshipId);
        return ResponseEntity.ok(updatedStudent);
    }

    @PostMapping("/{studentId}/progress/{internshipProgressId}")
    public ResponseEntity<Student> hasInternshipProgress(
            @PathVariable Long studentId,
            @PathVariable Long internshipProgressId,
            @RequestParam(required = false) String roles) {
        Student updatedStudent = studentService.hasInternshipProgress(studentId, internshipProgressId, roles);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}/progresses")
    public ResponseEntity<List<HasProgress>> getAllProgresses(@PathVariable Long studentId) {
        List<HasProgress> progresses = studentService.getAllProgresses(studentId);
        return ResponseEntity.ok(progresses);
    }

    @PutMapping("/{studentId}/update-progress/{internshipProgressId}")
    public ResponseEntity<Student> updateProgress(
            @PathVariable Long studentId,
            @PathVariable Long internshipProgressId,
            @RequestParam(required = false) String roles) {
        Student updatedStudent = studentService.updateProgress(studentId, internshipProgressId, roles);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}/delete-progress/{internshipProgressId}")
    public ResponseEntity<Student> deleteProgress(
            @PathVariable Long studentId,
            @PathVariable Long internshipProgressId) {
        Student updatedStudent = studentService.deleteProgress(studentId, internshipProgressId);
        return ResponseEntity.ok(updatedStudent);
    }

}