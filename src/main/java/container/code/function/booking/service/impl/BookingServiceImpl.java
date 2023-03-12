package container.code.function.booking.service.impl;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.*;
import container.code.data.repository.*;
import container.code.function.booking.BookingMapper;
import container.code.function.booking.api.AddBookingForm;
import container.code.function.booking.api.CountInfoDTO;
import container.code.function.booking.service.BookingService;
import container.code.function.booking.api.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingOrderRepository bookingOrderRepository;

    @Autowired
    OrderJobRepository orderJobRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    BookingMapper bookingMapper;
    private BookingOrder findBooking(Integer id) {
        BookingOrder existBooking = bookingOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
        return existBooking;
    }

    private OrderJob findOrderJob(Integer orderJob_id) {
        OrderJob existOrderJob = orderJobRepository.findById(orderJob_id).orElseThrow(() -> new NotFoundException("OrderJob not found"));
        return existOrderJob;
    }
    private Account findAccount(Integer acc_id) {
        Account existAcc = accountRepository.findById(acc_id).orElseThrow(() -> new NotFoundException("Account not found"));
        return existAcc;
    }
    private Job findJob(Integer job_id) {
        Job existJob = jobRepository.findById(job_id).orElseThrow(() -> new NotFoundException("Job not found"));
        return existJob;
    }

    @Override
    public void addBookingOrder(Integer renterId, Integer employeeId, Integer jobId, LocalDateTime timestamp,
                                Integer address_id, String status, String description, Integer workTime) {
        BookingOrder bookingOrder = new BookingOrder();
        Account renter = findAccount(renterId);
        Account employee = findAccount(employeeId);
        Address address = addressRepository.findAddressById(address_id);

        bookingOrder.setWorkHour(workTime);
        bookingOrder.setRenter(renter);
        bookingOrder.setEmployee(employee);
        bookingOrder.setStatus(status);
        bookingOrder.setTimestamp(timestamp);
        bookingOrder.setDescription(description);
        bookingOrder.setLocation(address.getDescription() + ", " + address.getDistrict().getName() + ", " + address.getDistrict().getProvince());
        BookingOrder saveBooking = bookingOrderRepository.save(bookingOrder);
        Job job = findJob(jobId);

        OrderJob orderJob = new OrderJob();
        orderJob.setBookingOrder(saveBooking);
        orderJob.setJob(job);
        orderJobRepository.save(orderJob);

    }

    @Override
    public void updateBookingOrder(Integer bookingOrderId, BookingOrder bookingOrder) {
        BookingOrder existBookingOrder = findBooking(bookingOrderId);
        existBookingOrder.setStatus(bookingOrder.getStatus());
//        existBookingOrder.setWorkTime(bookingOrder.getWorkTime());
        existBookingOrder.setDescription(bookingOrder.getDescription());
        bookingOrderRepository.save(existBookingOrder);
    }

    @Override
    public void deleteBookingOrder(Integer bookingOrderId) {
        BookingOrder existBookingOrder = findBooking(bookingOrderId);
        existBookingOrder.setStatus("Deleted");
        bookingOrderRepository.save(existBookingOrder);
//        bookingOrderRepository.delete(existBookingOrder);
    }

    @Override
    public List<BookingResponse> getBookingOrder(String status, Integer userId, Integer employeeId, Integer bookingId) {
        List<BookingResponse> bookingResponses = bookingOrderRepository.findAllByStatusId(status, userId, employeeId, bookingId)
                .stream().map(bookingMapper::toBookingResponse).collect(Collectors.toList());
        return bookingResponses;
    }

    @Override
    public ResponseEntity<ResponseObject> getBookingOrderCount() {
        CountInfoDTO counter = new CountInfoDTO();
        try {
            counter.setUserCount(accountRepository.countNotAdmin());
            counter.setFeedbackCount(feedbackRepository.count());
            counter.setConfirmedOrderCount(bookingOrderRepository.countWithStatus("confirmed"));
            counter.setFinishedOrderCount(bookingOrderRepository.countWithStatus("finished"));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), null, counter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur", null));
        }
    }

//    @Override
//    public ResponseEntity<ResponseObject> createBooking(AddBookingForm form) {
//        try {
//            BookingOrder order = new BookingOrder();
//            Optional<Account> employee = accountRepository.findById(form.getEmployeeId());
//            Optional<Account> renter = accountRepository.findById(form.getEmployeeId());
//
//            order.setEmployee(employee.get());
//            order.setRenter(renter.get());
//            order.setStatus("");
//
//            private Integer workHour;
//
//            private String location;
//
//            private String description;
//
//            private LocalDateTime workDate;
//
//            private LocalDateTime timestamp;
//
//            private List<Integer> jobsId;
//
//
//            List<Job> listJobs = new ArrayList<>();
//            for (Integer id : form.getJobsId()) {
//                Optional<Job> job = jobRepository.findById(id);
//                if (job.isPresent()) {
//                    OrderJob orderJob = new OrderJob();
//
//                }
//            }
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Create order successfully!", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur", null));
//        }
//    }
}
