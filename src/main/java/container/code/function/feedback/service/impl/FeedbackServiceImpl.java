package container.code.function.feedback.service.impl;

import container.code.data.dto.FeedbackResponse;
import container.code.data.dto.ResponseObject;
import container.code.data.entity.BookingOrder;
import container.code.data.entity.Feedback;
import container.code.data.repository.BookingOrderRepository;
import container.code.data.repository.FeedbackRepository;
import container.code.function.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    @Override
    public ResponseEntity<ResponseObject> getFeedback(Integer id) {
        try {
            List<BookingOrder> list = bookingOrderRepository.findByEmployeeId(id);
            List<FeedbackResponse> responseList = new ArrayList<>();
            for (BookingOrder bookingOrder: list) {
                FeedbackResponse newOrder = new FeedbackResponse();
                newOrder.setFeedbackId(bookingOrder.getFeedback().getId());
                newOrder.setRenterName(bookingOrder.getRenter().getFullname());
                newOrder.setRate(bookingOrder.getFeedback().getRate());
                newOrder.setDetail(bookingOrder.getFeedback().getDetail());
                newOrder.setTimestamp(bookingOrder.getFeedback().getTimestamp());
                newOrder.setProfilePicture(bookingOrder.getRenter().getProfilePicture());
                responseList.add(newOrder);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), null, responseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur", null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getFeedbackWithRate(Integer id, Integer rate) {
        try {
            List<BookingOrder> list = bookingOrderRepository.findByEmployeeIdAndRate(id, rate);
            List<FeedbackResponse> responseList = new ArrayList<>();
            for (BookingOrder bookingOrder: list) {
                FeedbackResponse newOrder = new FeedbackResponse();
                newOrder.setFeedbackId(bookingOrder.getFeedback().getId());
                newOrder.setRenterName(bookingOrder.getRenter().getFullname());
                newOrder.setRate(bookingOrder.getFeedback().getRate());
                newOrder.setDetail(bookingOrder.getFeedback().getDetail());
                newOrder.setTimestamp(bookingOrder.getFeedback().getTimestamp());
                newOrder.setProfilePicture(bookingOrder.getRenter().getProfilePicture());
                responseList.add(newOrder);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), null, responseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur", null));
        }
    }

//    @Override
//    public List<container.code.function.feedback.api.FeedbackResponse> getFeedbacks(int employee_id, int job_id, Integer rate) {
//        List<container.code.function.feedback.api.FeedbackResponse> feedbacks = feedbackRepository.findAllByEmployeeOrder(employee_id, job_id, rate).stream().map(feedbackMapper::toFeedbackResponse).collect(Collectors.toList());
//        return feedbacks;
//    }
//
//    private Feedback findFeedback(Integer id) {
//        Feedback existingFeedback = feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException("Feedback not found"));
//        return existingFeedback;
//    }
//
//    private EmployeeOrder findEmployeeOrder(Integer id) {
//        EmployeeOrder employeeOrder = employeeOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee order not found"));
//        return employeeOrder;
//    }
//
//    @Override
//    public boolean addFeedback(Integer id, Feedback feedback) throws IllegalAccessException {
//        boolean check = false;
//        EmployeeOrder employeeOrder = findEmployeeOrder(id);
//
//        if (employeeOrder.getFeedback().getId() != null) {
//            feedback.setEmployeeOrder(employeeOrder);
//            feedbackRepository.save(feedback);
//            employeeOrder.setFeedback(feedback);
//            employeeOrderRepository.save(employeeOrder);
//            check = true;
//        } else {
//            throw new IllegalAccessException("Already have a feedback");
//        }
//        return check;
//    }
//
//    public void updateFeedback(Feedback feedback) {
//        Feedback existingFeedback = findFeedback(feedback.getId());
//
//        existingFeedback.setDetail(feedback.getDetail());
//        existingFeedback.setRate(feedback.getRate());
//        existingFeedback.setTimestamp(feedback.getTimestamp());
//
//        feedbackRepository.save(existingFeedback);
//    }
//
//    public void deleteFeedback(Feedback feedback) {
//        Feedback existingFeedback = findFeedback(feedback.getId());
//        EmployeeOrder employeeOrder = findEmployeeOrder(existingFeedback.getEmployeeOrder().getId());
//        employeeOrder.setFeedback(null);
//        employeeOrderRepository.save(employeeOrder);
//        feedbackRepository.delete(existingFeedback);
//    }
}
