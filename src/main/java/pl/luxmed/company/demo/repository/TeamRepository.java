package pl.luxmed.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.luxmed.company.demo.entity.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
