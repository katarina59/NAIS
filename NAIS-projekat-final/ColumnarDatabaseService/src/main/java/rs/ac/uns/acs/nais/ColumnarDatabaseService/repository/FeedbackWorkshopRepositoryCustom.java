package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;

import java.util.List;


public interface FeedbackWorkshopRepositoryCustom {

    List<FeedbackAverageDTO> calculateStatisticsForWorkshop();
}
