package pl.luxmed.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.luxmed.company.demo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>  {
}
