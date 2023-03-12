package container.code.function.feedback.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class FeedbackResponse {
//    private Integer id;
//    private String detail;
//    private Integer rate;
//    private Date timestamp;
//    private Integer user_id;
//    private String userName;
//    private Integer employee_id;
//
//}
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private Integer feedbackId;
    private String renterName;
    private String profilePicture;
    private String detail;
    private Integer rate;
    private LocalDateTime timestamp;
}