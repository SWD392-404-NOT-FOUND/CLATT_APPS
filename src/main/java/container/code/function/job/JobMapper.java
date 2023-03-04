package container.code.function.job;


import container.code.data.entity.Job;
import container.code.function.job.api.JobResponse;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public JobResponse toJobResponse(Job job) {
        JobResponse jobResponse = new JobResponse();
        jobResponse.setId(job.getId());
        jobResponse.setJob_name(job.getName());
        jobResponse.setPrice(job.getPrice());
        jobResponse.setMeasure_unit(job.getMeasureUnit());
        jobResponse.setMeasure_value(job.getMeasureValue());
        jobResponse.setThumbnail_image(job.getThumbnailJobImage());
        return jobResponse;
    }
}
