package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;

import java.util.List;

public interface UserCustom {
    List<UserSessionStatisticsDTO> findUserSessionStatistics();
}
