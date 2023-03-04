package container.code.function.job;

import container.code.data.entity.Job;
import container.code.function.job.api.JobResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface JobService {
    List<JobResponse> getAllJob();

    JobResponse getJob(Integer id);

    void addJob(Job job, MultipartFile file) throws IOException;

    void updateJob(Job job, MultipartFile file) throws IOException;

    void deleteJob(Job job);
}
