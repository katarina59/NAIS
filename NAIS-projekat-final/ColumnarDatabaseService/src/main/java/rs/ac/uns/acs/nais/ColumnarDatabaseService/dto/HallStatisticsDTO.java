package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class HallStatisticsDTO {
    private String location;
    private long totalHalls;
    private int totalCapacity;
    private double avgBookingFee;

    // Getteri i setteri
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTotalHalls() {
        return totalHalls;
    }

    public void setTotalHalls(long totalHalls) {
        this.totalHalls = totalHalls;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public double getAvgBookingFee() {
        return avgBookingFee;
    }

    public void setAvgBookingFee(double avgBookingFee) {
        this.avgBookingFee = avgBookingFee;
    }
}
