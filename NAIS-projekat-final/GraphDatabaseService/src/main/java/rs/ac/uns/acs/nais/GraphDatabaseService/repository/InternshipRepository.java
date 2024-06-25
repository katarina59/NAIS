package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TopInternshipDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;

import java.util.List;

@Repository
public interface InternshipRepository extends Neo4jRepository<Internship, Long> {

    @Query("MATCH (s:Student {id: $studentId}) " +
            "MATCH (other:Student)-[:APPLIES_FOR]->(i: Internship) " +
            "WHERE other.facultyName = s.facultyName " +
            "WITH i, COUNT(other)-1 AS numApplicants " +
            "ORDER BY numApplicants DESC " +
            "RETURN i " +
            "LIMIT 1")
    Internship recommendInternshipByFaculty(Long studentId);


    @Query("MATCH (s:Student)-[:HAS_PROGRESS]->(ip:InternshipProgress)-[:PART_OF]->(i:Internship) " +
            "WHERE ip.endDate.year = 2023 " +
            "WITH i, i.category AS category, COUNT(s) AS numProgresses " +
            "ORDER BY category, numProgresses DESC " +
            "WITH category, COLLECT(i)[0] AS topInternship " +
            "RETURN topInternship")
    List<Internship> findTopInternshipsByCategory();

    @Query("MATCH (i: Internship {category: 'CHILD_AND_ADOLESCENT_PSYCHOLOG'}) " +
            "RETURN i")
    List<Internship> findInternshipsByChildAndAdolescentPsycholog();

    @Query("MATCH (i:Internship)<-[:PART_OF]-(ip:InternshipProgress)<-[:HAS_PROGRESS]-(s:Student)-[:GIVES_FEEDBACK]->(f:Feedback) " +
            "WITH AVG(f.rating) AS avgRating " +
            "WITH i, avgRating, AVG(avgRating) AS globalAvgRating " +
            "WHERE avgRating = globalAvgRating " +
            "RETURN i")
    List<Internship> recommendInternshipsByRating();

//    @Query("MATCH (i:Internship)<-[:PART_OF]-(ip:InternshipProgress)<-[:HAS_PROGRESS]-(s:Student)-[:GIVES_FEEDBACK]->(f:Feedback) " +
//            "WITH SUM(f.rating) AS globSum, COUNT(f) AS globNum " +
//            "WITH globSum / globNum AS globAvg " +
//            "MATCH (i)<-[:PART_OF]-(ip)-[:HAS_PROGRESS]->(s)-[:GIVES_FEEDBACK]->(f) " +
//            "WITH i, globAvg, AVG(f.rating) AS avgRating " +
//            "WHERE avgRating < globAvg " +
//            "RETURN i")
//    List<Internship> recommendInternshipsByRating();

}