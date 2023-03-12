package container.code.data.repository;

import container.code.data.entity.BookingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingOrderRepository extends JpaRepository<BookingOrder, Integer> {

    @Query("SELECT t FROM BookingOrder t WHERE t.id = ?1")
    BookingOrder findOrderById(Integer id);
    @Query("SELECT t FROM BookingOrder t WHERE t.employee.id = ?1")
    List<BookingOrder> findByEmployeeId(Integer id);

    @Query("SELECT t FROM BookingOrder t WHERE t.employee.id = ?1 and t.feedback.rate = ?2")
    List<BookingOrder> findByEmployeeIdAndRate(Integer id, Integer rate);

    @Query("SELECT t FROM BookingOrder t WHERE t.renter.id = ?1")
    List<BookingOrder> findByRenterId(Integer id);

    @Query("SELECT t.status FROM BookingOrder t WHERE t.id = ?1")
    String findStatusById(Integer id);

    @Modifying
    @Query("UPDATE BookingOrder t SET t.status = ?1 WHERE t.id = ?1")
    void updateStatus(Integer id, String status);

    @Query("SELECT t FROM BookingOrder t WHERE t.employee.id = ?1 and t.status = ?2")
    List<BookingOrder> findByEmployeeIdAndStatus(Integer id, String status);

    @Query("SELECT t FROM BookingOrder t WHERE t.status = ?1 and t.renter.id = ?2 and t.employee.id = ?3 and t.id = ?4")
    List<Map<String, Object>> findAllByStatusId(@Param("status") String status, @Param("userId") Integer userId,
                                                @Param("employeeId") Integer employeeId, @Param("bookingId") Integer bookingId);

    @Query("SELECT t FROM BookingOrder t WHERE t.status = ?1")
    long countWithStatus(String status);
}
