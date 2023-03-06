package container.code.function.feedback.service;

import container.code.data.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<ResponseObject> getFeedback(Integer id);

    ResponseEntity<ResponseObject> getFeedbackWithRate(Integer id, Integer rate);
}
