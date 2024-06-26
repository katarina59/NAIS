package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.PreparedStatementCreator;
import org.springframework.data.cassandra.core.cql.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopsUserCountDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.UserWorkshopRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserWorkshopRepositoryCustomImpl implements UserWorkshopRepositoryCustom {

    @Autowired
    private CqlTemplate template;

    @Override
    public List<WorkshopsUserCountDTO> getAttendeesByWorkshop(List<Long> workshop_ids) {
        String cqlQuery = "SELECT workshop_id, count(*) as userNum  FROM user_workshop where workshop_id in ? GROUP BY workshop_id";

        List<WorkshopsUserCountDTO> dtos = new ArrayList<>();

        template.query((PreparedStatementCreator) session -> session.prepare(cqlQuery),
                ps -> ps.bind(workshop_ids),
                (RowCallbackHandler) row -> {
                    WorkshopsUserCountDTO dto = new WorkshopsUserCountDTO(row.getLong("workshop_id"),
                            row.getLong("userNum"));
                            dtos.add(dto);
                });
        return dtos;
    }

    @Override
    public List<UserSessionStatisticsDTO> findUserSessionStatistics() {
        String query = "SELECT user_id, COUNT(workshop_id) AS total_sessions, AVG(session_duration) AS avg_session_duration " +
                "FROM user_workshop GROUP BY workshop_id ALLOW FILTERING";

        List<UserSessionStatisticsDTO> results = new ArrayList<>();
        template.query(query, (RowCallbackHandler) row -> {
            UserSessionStatisticsDTO dto = new UserSessionStatisticsDTO();
            dto.setUserId(row.getLong("user_id"));
            dto.setTotalSessions(row.getLong("total_sessions"));
            dto.setAvgSessionDuration(row.getInt("avg_session_duration"));
            dto.setUserId(row.getLong("user_id"));
            results.add(dto);
        });
        return results;
    }

}
