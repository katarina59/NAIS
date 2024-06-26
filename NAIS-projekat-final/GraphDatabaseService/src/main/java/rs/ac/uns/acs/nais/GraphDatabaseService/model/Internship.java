package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.enums.InternshipCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Node
public class Internship {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private InternshipCategory category;

    public Internship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InternshipCategory getCategory() {
        return category;
    }

    public void setCategory(InternshipCategory category) {
        this.category = category;
    }
}