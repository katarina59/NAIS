package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;

import java.util.List;

@Repository
public interface HallRepository extends CassandraRepository<Hall, Long>,HallCustom {

    @Query("SELECT * from halls WHERE hall_id = :id")
    Hall getById(@Param("id")Long id);

    @Query("SELECT min(booking_fee) from halls")
    Double getLowestPrice();

    @Query("SELECT hall_id from halls where booking_fee = :min_fee ALLOW FILTERING")
    Long getCheapestHall(@Param("min_fee")Double min_fee);

    @Query("SELECT * FROM halls WHERE booking_fee >= ?0 AND booking_fee <= ?1 ALLOW FILTERING")
    List<Hall> findHallsWithBookingFeeRange(Double minPrice, Double maxPrice);


}
