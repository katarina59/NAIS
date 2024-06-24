package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.FeedbackWorkshopRepositoryCustom;

import java.util.ArrayList;
import java.util.List;
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
}
