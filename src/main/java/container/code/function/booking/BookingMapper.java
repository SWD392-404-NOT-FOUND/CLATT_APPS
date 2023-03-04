package container.code.function.booking;

import container.code.function.booking.api.BookingResponse;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;

@Component
public class BookingMapper {
    public BookingResponse toBookingResponse(Map<String, Object> object) {
        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setId((Integer) object.get("id"));
        bookingResponse.setUser_id((Integer) object.get("user_id"));
        bookingResponse.setUser_name((String) object.get("user_name"));
        bookingResponse.setEmp_id((Integer) object.get("emp_id"));
        bookingResponse.setEmp_name((String) object.get("emp_name"));
        bookingResponse.setStatus((String) object.get("status"));
        bookingResponse.setWorkTime((Integer) object.get("workTime"));
        bookingResponse.setTimestamp((Date) object.get("timestamp"));
        bookingResponse.setLocation((String) object.get("location"));
        bookingResponse.setJob_id((Integer) object.get("job_id"));
        bookingResponse.setJob_name((String) object.get("job_name"));
        bookingResponse.setDescription((String) object.get("description"));

        return bookingResponse;
    }
}
