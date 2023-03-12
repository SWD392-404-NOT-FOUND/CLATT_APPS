package container.code.function.booking.service;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.BookingOrder;
import container.code.function.booking.api.AddBookingForm;
import container.code.function.booking.api.BookingResponse;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    void addBookingOrder(Integer renterId, Integer employeeId, Integer jobId, LocalDateTime timestamp,
                            Integer address_id, String status, String description, Integer workTime);
    void updateBookingOrder(Integer bookingOrderId, BookingOrder bookingOrder);
    void deleteBookingOrder(Integer bookingOrderId);
    List<BookingResponse> getBookingOrder(String status, Integer userId, Integer employeeId, Integer bookingId);

    ResponseEntity<ResponseObject> getBookingOrderCount();

//    ResponseEntity<ResponseObject> createBooking(AddBookingForm form);
}
