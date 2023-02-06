package container.code.data.repository;

import container.code.data.entity.BookingOrder;
import org.springframework.data.repository.CrudRepository;

public interface BookingOrderRepository extends CrudRepository<BookingOrder, Integer> {
}
