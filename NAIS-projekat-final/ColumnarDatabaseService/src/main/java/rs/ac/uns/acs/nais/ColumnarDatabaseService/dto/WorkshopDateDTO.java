package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.time.LocalDate;

public class WorkshopDateDTO {

    public String name;

    public LocalDate date;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }


}
