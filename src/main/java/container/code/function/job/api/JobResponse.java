package container.code.function.job.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponse {
    private Integer id;
    private String job_name;
    private Integer price;
    private String thumbnail_image;
    private Integer measure_value;
    private String measure_unit;

}
