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
public class Student{

    @Id
    private Long id;

    private String facultyName;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private double averageGrade;

    @Relationship(type="APPLIES_FOR", direction = Relationship.Direction.OUTGOING)
    private List<AppliesFor> internships = new ArrayList<>();

    @Relationship(type="HAS_PROGRESS", direction = Relationship.Direction.OUTGOING)
    private List<HasProgress> progress = new ArrayList<>();

    @Relationship(type = "GIVES_FEEDBACK", direction = Relationship.Direction.OUTGOING)
    private Feedback feedback;

    public Student() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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


    public List<AppliesFor> getInternships() {
        return internships;
    }

    public void setInternships(List<AppliesFor> internships) {
        this.internships = internships;
    }

    public List<HasProgress> getProgress() {
        return progress;
    }

    public void setProgress(List<HasProgress> progress) {
        this.progress = progress;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
