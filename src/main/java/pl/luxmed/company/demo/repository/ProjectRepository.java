package pl.luxmed.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.luxmed.company.demo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
