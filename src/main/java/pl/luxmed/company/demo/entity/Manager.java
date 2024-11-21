package pl.luxmed.company.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, length = 1000)
    private String email;

    @OneToMany(mappedBy = "manager")
    private List<Project> projects;

    @PreRemove
    private void preRemove() {
        if (projects != null) {
            projects.forEach(project -> project.setManager(null));
        }
    }

}