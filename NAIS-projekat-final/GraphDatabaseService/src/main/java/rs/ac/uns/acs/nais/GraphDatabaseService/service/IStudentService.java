package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.AppliesFor;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.HasProgress;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Student;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.Status;

import java.time.LocalDate;
import java.util.List;

public interface IStudentService {
    List<Student> findAllStudents();
    Student addNewStudent(Student product);
    boolean deleteStudent(Long id);
    boolean updateStudent(Student student);
    void updateStatus(Long internshipId);
    void updateDate(Long studentId);

    List<Student> recommendStudentsWithBestAvrageGrade(Long id);
    String findMostCommonRoleByStudentId(Long studentId);
    void updateRolesForSpecializedTherapies();
    Psychologist recommendMentorByInternshipProgress(Long studentId);
    Student findById(long id);
    Student deleteInternshipApplication(Long studentId, Long internshipId);
    Student updateInternshipApplication(Long studentId, Long internshipId, LocalDate applicationDate, Status status);
    Student applyForInternship(Long studentId, Long internshipId, LocalDate applicationDate, Status status);
    List<AppliesFor> getAllInternshipApplications(Long studentId);
    Student deleteProgress(Long studentId, Long internshipProgressId);
    Student updateProgress(Long studentId, Long internshipProgressId, String roles);
    Student hasInternshipProgress(Long studentId, Long internshipProgressId, String roles);
    List<HasProgress> getAllProgresses(Long studentId);


}