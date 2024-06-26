package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Psychologist{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;

    @Relationship(type = "IS_MENTORING", direction = Relationship.Direction.OUTGOING)
    private List<InternshipProgress> internshipProgress = new ArrayList<>();

    public Psychologist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<InternshipProgress> getInternshipProgress() {
        return internshipProgress;
    }

    public void setInternshipProgress(List<InternshipProgress> internshipProgress) {
        this.internshipProgress = internshipProgress;
    }
}
