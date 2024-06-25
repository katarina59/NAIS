package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.UserWorkshop;

import java.util.List;

@Repository
public interface UserWorkshopRepository extends CassandraRepository<UserWorkshop, Long>,UserCustom {

    @Query("SELECT * from user_workshop WHERE user_id = :u_id and workshop_id = :w_id")
    UserWorkshop getById(@Param("u_id")Long u_id, @Param("w_id") Long w_id);

    @Query("SELECT user_id FROM user_workshop WHERE workshop_id = ?0 ALLOW FILTERING")
    List<Long> findUserIdsByWorkshopId(Long workshopId);


}
