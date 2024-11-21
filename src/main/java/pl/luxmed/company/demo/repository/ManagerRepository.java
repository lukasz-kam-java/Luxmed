package pl.luxmed.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.luxmed.company.demo.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
