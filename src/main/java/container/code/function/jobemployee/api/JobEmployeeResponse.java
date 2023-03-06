package container.code.function.jobemployee.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import container.code.data.entity.Job;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobEmployeeResponse {
    private Integer empId;
    private Integer jobEmpId;
    private String empName;
    private String srcPicture;
    private String description;
    private int countRate;
    private Double rate;
    private String location;
    private String jobName;
    private Integer priceJob;

}
