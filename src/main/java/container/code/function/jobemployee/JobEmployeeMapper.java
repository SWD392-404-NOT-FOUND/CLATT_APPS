package container.code.function.jobemployee;

import container.code.data.entity.JobEmployee;
import container.code.data.entity.Province;
import container.code.function.jobemployee.api.JobEmployeeResponse;
import container.code.function.province.api.ProvinceResponse;
import org.springframework.stereotype.Component;

@Component
public class JobEmployeeMapper {
    public JobEmployeeResponse toEmpResponse(JobEmployee jobEmployee){
        JobEmployeeResponse jobEmployeeResponse = new JobEmployeeResponse();
        jobEmployeeResponse.setJobEmpId(jobEmployee.getId());
        jobEmployeeResponse.setEmpId(jobEmployee.getAccount().getId());
        jobEmployeeResponse.setEmpName(jobEmployee.getAccount().getFullname());
        jobEmployeeResponse.setDescription(jobEmployee.getAccount().getBio());
        jobEmployeeResponse.setSrcPicture(jobEmployee.getAccount().getProfilePicture());
        jobEmployeeResponse.setRate(4.9);
        jobEmployeeResponse.setCountRate(100);
        jobEmployeeResponse.setLocation("Thu Duc, Ho Chi Minh");
        jobEmployeeResponse.setJobName(jobEmployee.getJob().getName());
        jobEmployeeResponse.setPriceJob(jobEmployee.getJob().getPrice());
        return jobEmployeeResponse;
    }
}
