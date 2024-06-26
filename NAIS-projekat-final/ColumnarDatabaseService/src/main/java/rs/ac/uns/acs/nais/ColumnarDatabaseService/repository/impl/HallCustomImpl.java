package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.PreparedStatementBinder;
import org.springframework.data.cassandra.core.cql.PreparedStatementCreator;
import org.springframework.data.cassandra.core.cql.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.HallCustom;

@Repository
public class HallCustomImpl implements HallCustom {

    @Autowired
    private CqlTemplate cqlTemplate;

    @Override
    public HallStatisticsDTO findHallStatisticsByLocation(String location) {
        String query = "SELECT location, COUNT(hall_id) AS total_halls, SUM(capacity) AS total_capacity, " +
                "AVG(booking_fee) AS avg_booking_fee FROM halls WHERE location = ? ALLOW FILTERING";

        HallStatisticsDTO dto = new HallStatisticsDTO();

        cqlTemplate.query(session -> session.prepare(query),
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
