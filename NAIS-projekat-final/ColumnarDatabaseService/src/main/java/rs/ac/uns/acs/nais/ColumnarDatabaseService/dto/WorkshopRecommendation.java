package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkshopRecommendation {
    private Long workshopId;

    private int totalAttendees;

    public int getTotalAttendees() {
        return totalAttendees;
    }

    public void setTotalAttendees(int totalAttendees) {
        this.totalAttendees = totalAttendees;
    }

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

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getPsychologistId() {
        return psychologistId;
    }

    public void setPsychologistId(Long psychologistId) {
        this.psychologistId = psychologistId;
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

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public int getMax_attendees() {
        return max_attendees;
    }

    public void setMax_attendees(int max_attendees) {
        this.max_attendees = max_attendees;
    }

    public double getRecommendedPercentage() {
        return recommendedPercentage;
    }

    public void setRecommendedPercentage(double recommendedPercentage) {
        this.recommendedPercentage = recommendedPercentage;
    }

    public double getNotRecommendedPercentage() {
        return notRecommendedPercentage;
    }

    public void setNotRecommendedPercentage(double notRecommendedPercentage) {
        this.notRecommendedPercentage = notRecommendedPercentage;
    }

    private String category;

    private LocalDate date;

    private boolean is_online;

    private double price;

    private Long psychologistId;

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;

    private String name;

    private Long hallId;

    private int max_attendees;

    private double recommendedPercentage;
    private double notRecommendedPercentage;
}
