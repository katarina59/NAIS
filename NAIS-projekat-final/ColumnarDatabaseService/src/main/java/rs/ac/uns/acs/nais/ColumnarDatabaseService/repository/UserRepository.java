package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends CassandraRepository<User, Long> {

    @Query("SELECT * from users WHERE user_id = :id")
    User getById(@Param("id")Long id);

    @Query("SELECT user_id FROM users WHERE gender = 'male' ALLOW FILTERING")
    List<Long> getMaleUser();
    @Query("SELECT * FROM users WHERE user_id = :user_id")
    User getUserDataByFinalGrade(@Param("user_id")Long user_id);
}
