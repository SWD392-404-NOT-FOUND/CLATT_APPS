package container.code.function.jobemployee.impl;

import container.code.data.repository.JobEmployeeRepository;
import container.code.function.jobemployee.JobEmployeeMapper;
import container.code.function.jobemployee.JobEmployeeService;
import container.code.function.jobemployee.api.JobEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobEmployeeServiceImpl implements JobEmployeeService {
    @Autowired
    private JobEmployeeRepository jobEmployeeRepository;
    @Autowired
    private JobEmployeeMapper jobEmployeeMapper;
    @Override
    public List<JobEmployeeResponse> getEmpByJobId(Integer jobId) {
        return jobEmployeeRepository.findByJobId(jobId)
                .stream()
                .map(jobEmployeeMapper::toEmpResponse)
                .collect(Collectors.toList());
    }

    public JobEmployeeResponse getEmpByJobId(Integer jobId, Integer empId) {
        return jobEmployeeMapper.toEmpResponse(jobEmployeeRepository.findByJobIdEmpId(jobId, empId).get(0));
    }
}
