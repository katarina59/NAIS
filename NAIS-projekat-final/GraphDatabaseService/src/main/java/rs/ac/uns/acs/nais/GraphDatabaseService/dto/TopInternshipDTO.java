package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.InternshipCategory;

public class TopInternshipDTO {
    private InternshipCategory categoryName;
    private String topInternshipTitle;

    public TopInternshipDTO(InternshipCategory categoryName, String topInternshipTitle) {
        this.categoryName = categoryName;
        this.topInternshipTitle = topInternshipTitle;
    }

    public TopInternshipDTO() {
    }

    public InternshipCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(InternshipCategory categoryName) {
        this.categoryName = categoryName;
    }

    public String getTopInternshipTitle() {
        return topInternshipTitle;
    }

    public void setTopInternshipTitle(String topInternshipTitle) {
        this.topInternshipTitle = topInternshipTitle;
    }
}
