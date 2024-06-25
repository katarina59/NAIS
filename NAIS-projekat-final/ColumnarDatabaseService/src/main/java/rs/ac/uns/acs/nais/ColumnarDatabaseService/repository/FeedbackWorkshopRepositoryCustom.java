package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopCountProjection;

import java.util.List;


public interface FeedbackWorkshopRepositoryCustom {

    List<FeedbackAverageDTO> calculateStatisticsForWorkshop();

    FeedbackCountByMaleDTO countFeedbacksByMale(@Param("user_ids")List<Long> user_ids);

    List<WorkshopCountProjection> countRecommendedByWorkshop();

    List<WorkshopCountProjection> countNotRecommendedByWorkshop();

    List<WorkshopCountProjection> countTotalAttendeesByWorkshop();
}
