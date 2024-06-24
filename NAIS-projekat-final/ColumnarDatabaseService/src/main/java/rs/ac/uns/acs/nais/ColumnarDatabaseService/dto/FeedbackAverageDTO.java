package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;


public record FeedbackAverageDTO(Long workshopId, Double finalGrade, Double contentGrade, Double organizationGrade, Double priceGrade, Double psychologistGrade) {




}
