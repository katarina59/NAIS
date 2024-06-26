package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.LocationStatisticsDTO;

import java.util.List;

public interface HallRepositoryCustom {

   List<LocationStatisticsDTO> getLocationStatistics();
}
