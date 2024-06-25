package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Student;

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
}