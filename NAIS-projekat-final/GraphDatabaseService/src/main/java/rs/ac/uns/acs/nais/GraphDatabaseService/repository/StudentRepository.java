package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {
    @Query("MATCH (s:Student)-[r:APPLIES_FOR]->(i:Internship) " +
            "WHERE id(i) = $internshipId " +
            "RETURN s, collect(r), collect(i)")

    List<Student> findStudentsByInternshipId(Long internshipId);

    @Query("MATCH (internship:Internship)<-[:APPLIES_FOR]-(student:Student) " +
            "WHERE id(internship) = $internshipId " +
            "WITH internship, student, student.averageGrade AS studentGrade " +
            "WITH internship, AVG(studentGrade) AS averageFacultyGrade " +
            "MATCH (internship)<-[:APPLIES_FOR]-(student:Student) " +
            "WHERE student.averageGrade > averageFacultyGrade " +
            "RETURN student")
    List<Student> recommendStudentsForInternship(Long internshipId);

    @Query("MATCH (s:Student)-[hp:HAS_PROGRESS]->(ip:InternshipProgress) " +
            "WHERE s.id = $studentId " +
            "WITH hp.roles AS roles, COUNT(ip) AS count " +
            "ORDER BY count DESC " +
            "RETURN roles " +
            "LIMIT 1")
    String findMostCommonRoleByStudentId(Long studentId);

    @Query("MATCH (student:Student)-[:PARTICIPATED_IN]->(internship:Internship)-[:CATEGORY]->(category:Category)<-[:CATEGORY]-(recommendedInternship:Internship)<-[:PARTICIPATED_IN]-(recommendedStudent:Student)-[:MENTORED_BY]->(recommendedMentor:Psychologist) " +
            "WHERE student.id = $studentId " +
            "WITH recommendedMentor, COUNT(recommendedInternship) AS internshipCount " +
            "ORDER BY internshipCount DESC " +
            "LIMIT 1 " +
            "RETURN recommendedMentor")
    Psychologist recommendMentorByInternshipProgress(Long studentId);
}