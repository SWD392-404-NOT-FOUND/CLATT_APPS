package container.code.data.repository;

import container.code.data.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query(value = "SELECT f,f.employeeOrder.bookingOrder.account.id, f.employeeOrder.bookingOrder.account.fullname, f.employeeOrder.account.id " +
            "FROM Feedback f  LEFT JOIN f.employeeOrder eo " +
            "LEFT JOIN eo.account ac LEFT JOIN eo.bookingOrder bo " +
            "LEFT JOIN bo.orderJobs oj LEFT JOIN oj.job " +
            "WHERE ac.id = :employee_id AND oj.job.id = :job_id " +
            "AND (:rate IS NULL OR f.rate = :rate)")
    List<Feedback> findAllByEmployeeOrder(@Param("employee_id") int employeeId, @Param("job_id") int jobId, @Param("rate") Integer rate);
}
