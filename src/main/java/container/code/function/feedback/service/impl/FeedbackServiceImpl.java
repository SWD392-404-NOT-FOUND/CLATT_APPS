package container.code.function.feedback.service.impl;

import container.code.data.dto.FeedbackResponse;
import container.code.data.dto.ResponseObject;
import container.code.data.entity.BookingOrder;
import container.code.data.repository.BookingOrderRepository;
import container.code.data.repository.FeedbackRepository;
import container.code.function.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
