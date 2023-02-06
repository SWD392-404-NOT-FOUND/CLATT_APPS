package container.code.data.repository;

import container.code.data.entity.EmployeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeOrderRepository extends CrudRepository<EmployeeOrder, Integer> {
}
