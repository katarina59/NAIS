package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDateDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;

import java.util.List;

@Repository
public interface WorkshopRepository extends CassandraRepository<Workshop, Long> {
    @Query("SELECT * from workshops WHERE workshop_id = :id")
    Workshop getById(@Param("id")Long id);

    @Query("SELECT name, date FROM workshops WHERE hallId = :hall_id ALLOW FILTERING")
    List<WorkshopDateDTO> getByHall(@Param("hall_id")Long hall_id);
}
