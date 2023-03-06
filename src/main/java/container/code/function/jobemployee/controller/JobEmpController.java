package container.code.function.jobemployee.controller;

import container.code.function.jobemployee.JobEmployeeService;
import container.code.function.jobemployee.api.JobEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/job-by-emp")
public class JobEmpController {
    @Autowired
    private JobEmployeeService jobEmployeeService;

    @GetMapping("/all-by-job-id/{job_id}")
    public ResponseEntity<List<JobEmployeeResponse>> getAll(@PathVariable("job_id") Integer jobId) {
        return new ResponseEntity<List<JobEmployeeResponse>>(jobEmployeeService
                .getEmpByJobId(jobId), HttpStatus.OK);
    }

    @GetMapping("/get-emp-service")
    public ResponseEntity<JobEmployeeResponse> getAllByEmpId(@RequestParam("job_id") Integer jobId,
                                                             @RequestParam("emp_id") Integer empId) {
        return new ResponseEntity<JobEmployeeResponse>(jobEmployeeService.
                getEmpByJobId(jobId, empId), HttpStatus.OK);
    }
}
