package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.math.BigInteger;

public class FeedbackCountByMaleDTO {

    private long feedbackCount;

    private Double averagePriceGrade;

    public FeedbackCountByMaleDTO(long feedbackCount, Double averagePriceGrade) {
        this.feedbackCount = feedbackCount;
        this.averagePriceGrade = averagePriceGrade;
    }

    public long getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(long feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public void setAveragePriceGrade(Double averagePriceGrade) {
        this.averagePriceGrade = averagePriceGrade;
    }

    public FeedbackCountByMaleDTO() {
    }

    public Double getAveragePriceGrade() {
        return averagePriceGrade;
    }
}
