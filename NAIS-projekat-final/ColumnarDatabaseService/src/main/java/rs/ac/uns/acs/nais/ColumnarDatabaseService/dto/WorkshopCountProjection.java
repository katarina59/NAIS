package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class WorkshopCountProjection {
    long workshopId;

    public long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(long workshopId) {
        this.workshopId = workshopId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    long count;
}


