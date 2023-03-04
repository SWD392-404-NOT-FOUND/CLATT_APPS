package container.code.function.booking.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import container.code.data.entity.EmployeeOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {
    private Integer id;
    private Integer user_id;
    private String user_name;
    private Integer emp_id;
    private String emp_name;
    private String status;
    private Integer workTime;
    private Date timestamp;
//    private Integer price;
    private String location;
    private Integer job_id;
    private String job_name;
    private String description;

}
