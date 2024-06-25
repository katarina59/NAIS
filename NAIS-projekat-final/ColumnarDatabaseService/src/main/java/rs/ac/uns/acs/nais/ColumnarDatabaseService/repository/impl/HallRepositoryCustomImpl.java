package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.LocationStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.HallRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HallRepositoryCustomImpl implements HallRepositoryCustom {

    @Autowired
    private CqlTemplate template;

    @Override
    public List<LocationStatisticsDTO> getLocationStatistics() {
        String cqlQuery = "SELECT location, "
                + "sum(events_count) as eventsSum, "
                + "avg(booking_fee) as avgPrice "
                + "FROM halls "
                + "GROUP BY location";

        List<LocationStatisticsDTO> dtos = new ArrayList<>();

        template.query(cqlQuery, row -> {
            LocationStatisticsDTO dto = new LocationStatisticsDTO(row.getString("location"),
                    row.getInt("eventsSum"),
                    row.getDouble("avgPrice"));
            dtos.add(dto);
        });
        return dtos;
    }
}
