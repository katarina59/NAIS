package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Internship;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.InternshipProgress;

@Repository
public interface InternshipProgressRepository extends Neo4jRepository<InternshipProgress, Long> {
}