package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.time.LocalDateTime;

public class UserSessionStatisticsDTO {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(long totalSessions) {
        this.totalSessions = totalSessions;
    }

    public int getAvgSessionDuration() {
        return avgSessionDuration;
    }

    public void setAvgSessionDuration(int avgSessionDuration) {
        this.avgSessionDuration = avgSessionDuration;
    }

    private String gender;


    private String name;


    private String lastName;

    private long totalSessions;

    private int avgSessionDuration;
}

