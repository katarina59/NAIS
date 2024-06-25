package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class WorkshopsUserCountDTO {

    private Long workshop_id;

    private Long userNum;

    public WorkshopsUserCountDTO(Long workshop_id, Long userNum) {
        this.workshop_id = workshop_id;
        this.userNum = userNum;
    }

    public Long getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(Long workshop_id) {
        this.workshop_id = workshop_id;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Long getUserNum() {
        return userNum;
    }
}
