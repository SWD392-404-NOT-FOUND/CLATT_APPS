package container.code.function.booking.service;

import container.code.data.entity.BookingOrder;
import container.code.function.booking.api.BookingResponse;

import java.sql.Date;
import java.util.List;

public interface BookingService {
    void addBookingOrder(Integer userId, Integer employeeId, Integer jobId, Date timestamp,
                            Integer address_id, String status, String description, Integer workTime);
    void updateBookingOrder(Integer bookingOrderId, BookingOrder bookingOrder);
    void deleteBookingOrder(Integer bookingOrderId);
    List<BookingResponse> getBookingOrder(String status, Integer userId, Integer employeeId, Integer bookingId);
}
