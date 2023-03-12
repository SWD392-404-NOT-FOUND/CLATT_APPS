package container.code.function.booking.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import container.code.data.entity.Account;
import container.code.data.entity.Feedback;
import container.code.data.entity.OrderJob;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AddBookingForm {

    private Integer renterId;

    private Integer employeeId;

    private Integer workHour;

    private String location;

    private String description;

    private LocalDateTime workDate;

    private LocalDateTime timestamp;

    private List<Integer> jobsId;
}
