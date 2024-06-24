package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("workshops")
public class Workshop {


    @PrimaryKeyColumn(name= "workshop_id", type= PrimaryKeyType.PARTITIONED)
    private Long workshopId;

    @PrimaryKeyColumn(name = "category", ordinal = 0 , ordering = Ordering.ASCENDING)
    private String category;

    @PrimaryKeyColumn(name = "date", ordinal = 1, ordering = Ordering.ASCENDING)
    private LocalDateTime date;

    private boolean is_online;

    private double price;

    private Long psychologistId;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
