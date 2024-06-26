package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopCountProjection;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.FeedbackWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.UserWorkshop;

import java.util.List;

@Repository
public interface FeedbackWorkshopRepository extends CassandraRepository<FeedbackWorkshop, Long>, FeedbackWorkshopRepositoryCustom {

    @Query("SELECT * from feedbacks WHERE workshop_id = :w_id and user_id = :u_id")
    FeedbackWorkshop getById(@Param("w_id")Long w_id, @Param("u_id") Long u_id);

    @Query("SELECT max(final_grade) FROM feedbacks")
    Double getMaxFinalGrade();
    @Query("SELECT user_id FROM feedbacks where final_grade = :max_final_grade ALLOW FILTERING")
    Long getUserIdByFinalGrade(@Param("max_final_grade")Double max_final_grade);




}


