package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Student;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.StudentService;

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

}