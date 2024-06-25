package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.*;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.FeedbackWorkshopRepositoryCustom;

import java.math.BigInteger;
import java.util.*;

@Repository
public class FeedbackWorkshopRepositoryCustomImpl implements FeedbackWorkshopRepositoryCustom {

    @Autowired
    private CqlTemplate template;

    @Override
    public List<FeedbackAverageDTO> calculateStatisticsForWorkshop() {
        String query = "SELECT " +
                "workshop_id , " +
                "CAST(AVG(final_grade) * 100 AS bigint) / 100.0 as finalGrade, " +
                "CAST(AVG(content_grade) * 100 AS bigint) / 100.0 as contentGrade, " +
                "CAST(AVG(organization_grade) * 100 AS bigint) / 100.0 as organizationGrade, " +
                "CAST(AVG(price_grade) * 100 AS bigint) / 100.0 as priceGrade , " +
                "CAST(AVG(psychologist_grade) * 100 AS bigint) / 100.0  as psychologistGrade " +
                "FROM feedbacks " +
                "GROUP BY workshop_id;";

        List<FeedbackAverageDTO> dtos = new ArrayList<>();
        template.query(query, row -> {FeedbackAverageDTO dto = new FeedbackAverageDTO(row.getLong("workshop_id"),
                row.getDouble("finalGrade"),
                row.getDouble("contentGrade"),
                row.getDouble("organizationGrade"),
                row.getDouble("priceGrade"),
                row.getDouble("psychologistGrade"));
                dtos.add(dto);
        });

        return dtos;
    }

    @Override
    public FeedbackCountByMaleDTO countFeedbacksByMale(List<Long> user_ids) {
        String cqlQuery = "SELECT count(*) as feedbackCount, avg(price_grade) as averagePriceGrade FROM feedbacks WHERE user_id in ? ALLOW FILTERING";

        FeedbackCountByMaleDTO dto = new FeedbackCountByMaleDTO();

        template.query((PreparedStatementCreator) session -> session.prepare(cqlQuery),
                ps -> ps.bind(user_ids),
                (RowCallbackHandler) row -> {dto.setFeedbackCount(row.getLong("feedbackCount")); dto.setAveragePriceGrade(row.getDouble("averagePriceGrade"));}
        );

        return dto;
    }
}
