package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

public class FeedbackWorkshopDTO {

    private Long workshopId;

    private Long userId;

    private Double finalGrade;

    private Double contentGrade;

    private Double organizationGrade;

    private Double priceGrade;


    private Double psychologistGrade;

    private boolean recommended;

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Double getContentGrade() {
        return contentGrade;
    }

    public void setContentGrade(Double contentGrade) {
        this.contentGrade = contentGrade;
    }

    public Double getOrganizationGrade() {
        return organizationGrade;
    }

    public void setOrganizationGrade(Double organizationGrade) {
        this.organizationGrade = organizationGrade;
    }

    public Double getPriceGrade() {
        return priceGrade;
    }

    public void setPriceGrade(Double priceGrade) {
        this.priceGrade = priceGrade;
    }

    public Double getPsychologistGrade() {
        return psychologistGrade;
    }

    public void setPsychologistGrade(Double psychologistGrade) {
        this.psychologistGrade = psychologistGrade;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}
