package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class MaxCapacityHallWorkshopCountDTO {

    private Long hallId;

    private String location;

    private String name;

    private int capacity;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(Double bookingFee) {
        this.bookingFee = bookingFee;
    }

    public int getWorkshopsCount() {
        return workshopsCount;
    }

    public void setWorkshopsCount(int workshopsCount) {
        this.workshopsCount = workshopsCount;
    }

    private Double bookingFee;

    private int workshopsCount;

}
