package rs.ac.uns.acs.nais.GraphDatabaseService.repository;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Psychologist;

import java.util.Optional;

public interface PsychologistRepository extends Neo4jRepository<Psychologist, Long> {

    @Query("MATCH (psychologist:Psychologist)-[:IS_MENTORING]->(progress:InternshipProgress)-[:PART_OF]->(internship:Internship) " +
            "WHERE progress.endDate.year = 2023 " +
            "MATCH (progress)<-[:HAS_PROGRESS]-(student:Student)-[:GIVES_FEEDBACK]->(feedback:Feedback) " +
            "WITH psychologist, SUM(feedback.rating) AS totalRating, COUNT(feedback) AS numFeedbacks " +
            "WHERE numFeedbacks > 0 " +
            "RETURN psychologist, totalRating / numFeedbacks AS averageRating " +
            "ORDER BY averageRating DESC " +
            "LIMIT 1")
    Psychologist recommendMentorWithHighestAverageRating();


    @Query("MATCH (category:Internship {category: $category}) " +
            "MATCH (psychologist:Psychologist)-[:IS_MENTORING]->(progress:InternshipProgress)-[:PART_OF]->(category) " +
            "WITH psychologist, count(progress) AS progressCount " +
            "ORDER BY progressCount DESC " +
            "RETURN psychologist " +
            "LIMIT 1")
    Optional<Psychologist> findTopMentorByInternshipCategory(@Param("category") String category);



}
