package solo.practice.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.practice.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
