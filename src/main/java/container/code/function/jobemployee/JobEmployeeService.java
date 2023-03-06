package container.code.function.jobemployee;

import container.code.function.jobemployee.api.JobEmployeeResponse;

import java.util.List;

public interface JobEmployeeService {
    public List<JobEmployeeResponse> getEmpByJobId(Integer jobId);
    public JobEmployeeResponse getEmpByJobId(Integer jobId, Integer empId);
}
