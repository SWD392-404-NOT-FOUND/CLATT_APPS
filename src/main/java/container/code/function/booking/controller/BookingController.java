package container.code.function.booking.controller;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.BookingOrder;
import container.code.function.booking.api.AddBookingForm;
import container.code.function.booking.service.BookingService;
import container.code.function.booking.api.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/get-bookings")
    public List<BookingResponse> getBooking(@RequestParam(required = false) String status,
                                            @RequestParam(required = false) Integer user_id,
                                            @RequestParam(required = false) Integer employee_id,
                                            @RequestParam(required = false) Integer booking_id) {
        return bookingService.getBookingOrder(status, user_id, employee_id, booking_id);
    }

    @GetMapping("/get-bookings-count")
    public ResponseEntity<ResponseObject> getBookingCount() {
        return bookingService.getBookingOrderCount();
    }

//    @PostMapping("/create-booking")
//    public ResponseEntity<ResponseObject> createBooking(@RequestBody AddBookingForm form) {
//        return bookingService.createBooking(form);
//    }

    @PutMapping("/update/{booking_id}")
    public ResponseEntity updateBooking(@PathVariable("booking_id") int booking_id,
                                        @RequestBody(required = false) BookingOrder bookingOrder) {
        try {
            bookingService.updateBookingOrder(booking_id, bookingOrder);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{booking_id}")
    public ResponseEntity deleteBooking(@PathVariable("booking_id") int booking_id) {
        try {
            bookingService.deleteBookingOrder(booking_id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}