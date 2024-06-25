package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.RowCallbackHandler;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.UserCustom;

import java.util.ArrayList;
import java.util.List;

public class UserCustomImpl implements UserCustom {

    @Autowired
    private CqlTemplate template;
    @Override
    public List<UserSessionStatisticsDTO> findUserSessionStatistics() {
        String query = "SELECT user_id, COUNT(workshop_id) AS total_sessions, AVG(session_duration) AS avg_session_duration " +
                "FROM user_workshop GROUP BY user_id ALLOW FILTERING";

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
