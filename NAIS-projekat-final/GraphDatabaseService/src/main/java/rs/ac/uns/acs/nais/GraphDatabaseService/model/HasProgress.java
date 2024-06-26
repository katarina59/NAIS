package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class HasProgress {
    @RelationshipId
    private  Long id;
    private String roles;
    @TargetNode
    private  InternshipProgress internshipProgress;

    public HasProgress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public InternshipProgress getInternshipProgress() {
        return internshipProgress;
    }

    public void setInternshipProgress(InternshipProgress internshipProgress) {
        this.internshipProgress = internshipProgress;
    }
}
