package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Table("workshops")
public class Workshop {


    @PrimaryKeyColumn(name= "workshop_id", type= PrimaryKeyType.PARTITIONED)
    private Long workshopId;

    @PrimaryKeyColumn(name = "category", ordinal = 0 , ordering = Ordering.ASCENDING)
    private String category;

    @PrimaryKeyColumn(name = "date", ordinal = 1, ordering = Ordering.ASCENDING)
    private LocalDate date;

    @PrimaryKeyColumn(name = "hallid", ordinal = 2, ordering = Ordering.ASCENDING)
    private Long hallId;

    private boolean is_online;

    private double price;

    private Long psychologistId;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;

    private String name;



    private int max_attendees;

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public double getPrice() {
        return price;
    }

    public Long getHallId() {
        return hallId;
    }


    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getPsychologistId() {
        return psychologistId;
    }

    public void setPsychologistId(Long psychologistId) {
        this.psychologistId= psychologistId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_attendees() {
        return max_attendees;
    }

    public void setMax_attendees(int maxAttendees) {
        this.max_attendees = maxAttendees;
    }
}
