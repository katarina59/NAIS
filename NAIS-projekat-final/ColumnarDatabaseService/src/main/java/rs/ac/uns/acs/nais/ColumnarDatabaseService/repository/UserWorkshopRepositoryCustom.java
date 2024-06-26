package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.repository.query.Param;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopsUserCountDTO;

import java.util.List;

public interface UserWorkshopRepositoryCustom {

    List<WorkshopsUserCountDTO> getAttendeesByWorkshop(@Param("workshop_ids")List<Long> workshop_ids);

    List<UserSessionStatisticsDTO> findUserSessionStatistics();
}
