package container.code.data.repository;

import container.code.data.entity.BookingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookingOrderRepository extends JpaRepository<BookingOrder, Integer> {

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

    @Query(value =
    "SELECT bo.id as id, " +
            "a.id as user_id, a.fullname as user_name, emp.id as emp_id, emp.fullname as emp_name, " +
            "bo.status as status, bo.workTime as workTime, bo.timestamp as timestamp, bo.location as location, " +
            "j.id as job_id, j.name as job_name, bo.description as description " +
    "FROM BookingOrder bo " +
            "INNER JOIN bo.account a " +
            "INNER JOIN bo.orderJobs oj " +
            "INNER JOIN bo.employeeOrders eo " +
            "INNER JOIN eo.account emp " +
            "INNER JOIN oj.job j " +
    "WHERE (:status IS NULL OR bo.status = :status) " +
            "AND (:bookingId IS NULL OR bo.id = :bookingId)" +
            "AND (:userId IS NULL OR a.id = :userId) " +
            "AND (:employeeId IS NULL OR emp.id = :employeeId)", nativeQuery = false)
    List<Map<String, Object>> findAllByStatusId(@Param("status") String status, @Param("userId") Integer userId,
                                                @Param("employeeId") Integer employeeId, @Param("bookingId") Integer bookingId);
}
