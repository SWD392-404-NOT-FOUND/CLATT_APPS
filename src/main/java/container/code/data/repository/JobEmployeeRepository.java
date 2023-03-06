package container.code.data.repository;

import container.code.data.entity.JobEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobEmployeeRepository extends JpaRepository<JobEmployee, Integer> {
    @Query(value = "SELECT je, je.account.id, je.account.fullname, je.account.bio, " +
            " je.account.profilePicture, je.job.name, je.job.id " +
            " FROM JobEmployee je " +
            " LEFT JOIN je.job LEFT JOIN je.account " +
            "WHERE je.job.id = :jobId" ,nativeQuery = false)
    List<JobEmployee> findByJobId(@Param("jobId") Integer jobId);

    @Query(value = "SELECT distinct je, je.account.id, je.account.fullname, je.account.bio, " +
            " je.account.profilePicture, je.job.name, je.job.id " +
            " FROM JobEmployee je " +
            " LEFT JOIN je.job LEFT JOIN je.account " +
            "WHERE je.job.id = :job_id" +
            " AND je.account.id = :emp_id" ,nativeQuery = false)
    List<JobEmployee> findByJobIdEmpId(@Param("job_id") Integer jobId,
                                 @Param("emp_id") Integer empId);

}
