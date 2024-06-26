package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.PreparedStatementBinder;
import org.springframework.data.cassandra.core.cql.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;
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

    @Override
    public HallStatisticsDTO findHallStatisticsByLocation(String location) {
        String query = "SELECT location, COUNT(hall_id) AS total_halls, SUM(capacity) AS total_capacity, " +
                "AVG(booking_fee) AS avg_booking_fee FROM halls WHERE location = ? ALLOW FILTERING";

        HallStatisticsDTO dto = new HallStatisticsDTO();

        template.query(session -> session.prepare(query),
                (PreparedStatementBinder) ps -> ps.bind(location),
                (RowCallbackHandler) row -> {
                    dto.setLocation(row.getString("location"));
                    dto.setTotalHalls(row.getLong("total_halls"));
                    dto.setTotalCapacity(row.getInt("total_capacity"));
                    dto.setAvgBookingFee(row.getDouble("avg_booking_fee"));
                }
        );
        return dto;
    }
}
