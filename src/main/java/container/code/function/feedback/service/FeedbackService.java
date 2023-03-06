package container.code.function.feedback.service;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.Feedback;
import container.code.function.feedback.api.FeedbackResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedbackService {

    ResponseEntity<ResponseObject> getFeedback(Integer id);

    ResponseEntity<ResponseObject> getFeedbackWithRate(Integer id, Integer rate);

//    List<FeedbackResponse> getFeedbacks(int employee_id, int job_id, Integer rate);
//
//    boolean addFeedback(Integer id, Feedback feedback) throws IllegalAccessException;
//
//    void updateFeedback(Feedback feedback);
//
//    void deleteFeedback(Feedback feedback);

}
