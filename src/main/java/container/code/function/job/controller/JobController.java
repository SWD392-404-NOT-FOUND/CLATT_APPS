package container.code.function.job.controller;

import container.code.data.entity.Job;
import container.code.function.job.JobService;
import container.code.function.job.api.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/get-jobs")
    public List<JobResponse> getJobs() {
        return jobService.getAllJob();
    }

    @PostMapping("/get-job")
    public JobResponse getJob(@PathVariable("job_id") int job_id) {
        return jobService.getJob(job_id);
    }

    @PostMapping(value = "/create-job", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createJob(@RequestPart(required = true) MultipartFile file,
                                            @RequestParam("name") String name,
                                            @RequestParam("measure_value") Integer measure_value,
                                            @RequestParam("measure_unit") String measure_unit,
                                            @RequestParam("price") Integer price) {
        try {
            Job job = new Job();
            job.setName(name);
            job.setMeasureUnit(measure_unit);
            job.setMeasureValue(measure_value);
            job.setPrice(price);
            jobService.addJob(job, file);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //error
    @PutMapping(value = "/{job_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateJob(@RequestPart(required = false) MultipartFile file, @PathVariable("job_id") Integer job_id,
                                    @RequestParam(required = false, value = "name") String name,
                                    @RequestParam(required = false, value = "measure_value") Integer measure_value,
                                    @RequestParam(required = false, value = "measure_unit") String measure_unit,
                                    @RequestParam(required = false, value = "price") Integer price) {
        try {
            Job job = new Job();
            job.setId(job_id);
            job.setName(name);
            job.setMeasureUnit(measure_unit);
            job.setMeasureValue(measure_value);
            job.setPrice(price);
            jobService.updateJob(job, file);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity deleteJob(@PathVariable("job_id") int job_id) {
        try {
            Job job = new Job();
            job.setId(job_id);
            jobService.deleteJob(job);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}