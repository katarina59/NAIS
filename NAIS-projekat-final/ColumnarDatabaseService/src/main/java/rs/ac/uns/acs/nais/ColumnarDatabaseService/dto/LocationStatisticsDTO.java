package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class LocationStatisticsDTO {

    private String location;

    private int eventsSum;

    private double avgPrice;

    public LocationStatisticsDTO(String location, int eventsSum, double avgPrice) {
        this.location = location;
        this.eventsSum = eventsSum;
        this.avgPrice = avgPrice;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventsSum(int eventsSum) {
        this.eventsSum = eventsSum;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getLocation() {
        return location;
    }

    public long getEventsSum() {
        return eventsSum;
    }

    public double getAvgPrice() {
        return avgPrice;
    }
}
