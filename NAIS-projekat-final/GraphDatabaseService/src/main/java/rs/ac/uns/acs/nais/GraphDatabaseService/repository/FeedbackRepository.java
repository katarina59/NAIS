package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Feedback;

import java.util.List;

@Repository
public interface FeedbackRepository extends Neo4jRepository<Feedback, Long> {
        @Query("MATCH (f: Feedback) " +
                "WHERE f.rating > 3 " +
                "RETURN f")
        List<Feedback> findAllAboveThree();
}
