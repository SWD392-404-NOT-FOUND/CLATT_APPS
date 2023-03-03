package container.code.data.repository;

import container.code.data.entity.EmployeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeOrderRepository extends JpaRepository<EmployeeOrder, Integer> {
}
