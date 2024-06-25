package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;

public interface HallCustom {
    HallStatisticsDTO findHallStatisticsByLocation(String location);
}
