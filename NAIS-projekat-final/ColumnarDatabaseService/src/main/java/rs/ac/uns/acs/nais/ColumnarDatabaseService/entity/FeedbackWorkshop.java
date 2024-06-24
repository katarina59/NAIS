package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("feedbacks")
public class FeedbackWorkshop {

    @PrimaryKeyColumn(name = "workshop_id", type = PrimaryKeyType.PARTITIONED)
    private Long workshopId;

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, ordering = Ordering.ASCENDING)
    private Long userId;

    @Column("final_grade")
    private Double finalGrade;

    @Column("content_grade")
    private Double contentGrade;

    @Column("organization_grade")
    private Double organizationGrade;

    @Column("price_grade")
    private Double priceGrade;

    @Column("psychologist_grade")
    private Double psychologistGrade;

    @Column("recommended")
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
