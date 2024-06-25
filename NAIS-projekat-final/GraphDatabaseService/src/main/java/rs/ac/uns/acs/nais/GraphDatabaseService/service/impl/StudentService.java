package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.InternshipCategory;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.Status;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.InternshipRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.StudentRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IStudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    public final StudentRepository studentRepository;

    @Autowired
    public final InternshipRepository internshipRepository;

    public StudentService(StudentRepository studentRepository, InternshipRepository internshipRepository) {
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
    }
    @Override
    public List<Student> findAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student addNewStudent(Student student) {
        Student student1 = studentRepository.save(student);

        return student1;
    }

    @Override
    public boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        Optional<Student> student1 = studentRepository.findById(student.getId());
        if(student1.isPresent()){
            student1.get().setFacultyName(student.getFacultyName());
            student1.get().setName(student.getName());
            student1.get().setLastName(student.getLastName());
            student1.get().setEmail(student.getEmail());
            student1.get().setUsername(student.getUsername());
            student1.get().setPassword(student.getPassword());
            studentRepository.save(student1.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void updateStatus(Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId).orElse(null);
        if (internship == null) {
            throw new RuntimeException("Internship not found");
        }
        List<Student> students = studentRepository.findStudentsByInternshipId(internshipId);
        for (Student student : students) {
            for (AppliesFor appliesFor : student.getInternships()) {
                if (appliesFor.getInternship().getId().equals(internshipId)) {
                    appliesFor.setStatus(Status.REVIEWED);
                }
            }
            studentRepository.save(student);
        }
    }

    @Override
    @Transactional
    public void updateDate(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            LocalDate today = LocalDate.now();
            boolean updated = false;
            for (AppliesFor appliesFor : student.getInternships()) {
                if (appliesFor.getApplicationDate() == null) {
                    appliesFor.setApplicationDate(today);
                    updated = true;
                }
            }
            if (updated) {
                studentRepository.save(student);
            }
        }
    }

    @Override
    public List<Student> recommendStudentsWithBestAvrageGrade(Long id) {
        return studentRepository.recommendStudentsForInternship(id);
    }

    @Override
    public String findMostCommonRoleByStudentId(Long studentId) {
        String role = studentRepository.findMostCommonRoleByStudentId(studentId);
        return role;
    }

    public void updateRolesForSpecializedTherapies() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            boolean updated = false;
            for (HasProgress hasProgress : student.getProgress()) {
                if (isSpecializedTherapyCategory(hasProgress.getInternshipProgress().getInternship().getCategory())) {
                    hasProgress.setRoles("therapy_psychologist");
                    updated = true;
                }
            }
            if (updated) {
                studentRepository.save(student);
            }
        }
    }

    private boolean isSpecializedTherapyCategory(InternshipCategory category) {
        return category == InternshipCategory.ADDICTION_REHABILITATION ||
                category == InternshipCategory.TRAUMA_COUNSELING ||
                category == InternshipCategory.CHILD_AND_ADOLESCENT_PSYCHOLOG;
    }


    public Psychologist recommendMentorByInternshipProgress(Long studentId) {
        return studentRepository.recommendMentorByInternshipProgress(studentId);
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElse(null);
    }
}