package container.code.function.booking.impl;

import container.code.data.entity.*;
import container.code.data.repository.*;
import container.code.function.booking.BookingMapper;
import container.code.function.booking.BookingService;
import container.code.function.booking.api.BookingResponse;
import container.code.function.feedback.FeedbackMapper;
import container.code.function.feedback.api.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingOrderRepository bookingOrderRepository;

    @Autowired
    EmployeeOrderRepository employeeOrderRepository;
    @Autowired
    OrderJobRepository orderJobRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    BookingMapper bookingMapper;
    private BookingOrder findBooking(Integer id) {
        BookingOrder existBooking = bookingOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
        return existBooking;
    }
    private EmployeeOrder findEmpOrder(Integer emp_id) {
        EmployeeOrder existEmpOrder = employeeOrderRepository.findById(emp_id).orElseThrow(() -> new NotFoundException("EmpOrder not found"));
        return existEmpOrder;
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
    public void addBookingOrder(Integer userId, Integer employeeId, Integer jobId, Date timestamp,
                                Integer address_id, String status, String description, Integer workTime) {
        BookingOrder bookingOrder = new BookingOrder();
        Account accountUser = findAccount(userId);

        //Get Location
        //Not yet

        bookingOrder.setWorkTime(workTime);
        bookingOrder.setAccount(accountUser);
        bookingOrder.setStatus(status);
        bookingOrder.setTimestamp(timestamp);
        bookingOrder.setDescription(description);
        bookingOrder.setLocation("903 Đường D1 Khu Công Nghệ Cao, Thành phố Thủ Đức, Thành Phố Hồ Chí Minh");
        BookingOrder saveBooking = bookingOrderRepository.save(bookingOrder);

        Account accountEmp = findAccount(employeeId);
        Job job = findJob(jobId);

        EmployeeOrder employeeOrder = new EmployeeOrder();
        employeeOrder.setAccount(accountEmp);
        employeeOrder.setBookingOrder(saveBooking);
        employeeOrderRepository.save(employeeOrder);

        OrderJob orderJob = new OrderJob();
        orderJob.setBookingOrder(saveBooking);
        orderJob.setJob(job);
        orderJobRepository.save(orderJob);

    }

    @Override
    public void updateBookingOrder(Integer bookingOrderId, BookingOrder bookingOrder) {
        BookingOrder existBookingOrder = findBooking(bookingOrderId);
        existBookingOrder.setStatus(bookingOrder.getStatus());
        existBookingOrder.setWorkTime(bookingOrder.getWorkTime());
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
}
