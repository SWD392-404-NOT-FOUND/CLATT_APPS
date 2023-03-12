package container.code.function.feedback.controller;

import container.code.data.dto.ResponseObject;
import container.code.function.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("getFeedback")
    @PreAuthorize("hasAnyAuthority('admin','renter','employee')")
    public ResponseEntity<ResponseObject> getFeedback(
            @RequestParam(name = "account_id") Integer id) {
        return feedbackService.getFeedback(id);
    }

    @GetMapping("getFeedbackCount")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<ResponseObject> getFeedbackCount() {
        return feedbackService.getFeedbackCount();
    }

    @GetMapping("getFeedbackWithRate")
    @PreAuthorize("hasAnyAuthority('admin','renter','employee')")
    public ResponseEntity<ResponseObject> getFeedbackWithRate(
            @RequestParam(name = "account_id") Integer id,
            @RequestParam(name = "rate") Integer rate) {
        return feedbackService.getFeedbackWithRate(id, rate);
    }

//    @PostMapping("/get-feedbacks")
//    public List<FeedbackResponse> getFeedback(@RequestParam int employee_id, @RequestParam int job_id, @RequestParam(required = false) Integer rate) {
//        return feedbackService.getFeedbacks(employee_id, job_id, rate);
//    }
//    @PostMapping("/create-feedback/{id}")
//    public ResponseEntity<String> createFeedback(@PathVariable("order_id") Integer orderId, @RequestBody Feedback feedback) {
//        try {
//            feedback.setId(null);
//            boolean check = feedbackService.addFeedback(orderId, feedback);
//            if (check) {
//                return new ResponseEntity(HttpStatus.CREATED);
//            } else {
//                return ResponseEntity.ok().body("Can not add feedback");
//            }
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping("/{feedbackId}")
//    public ResponseEntity updateFeedback(@PathVariable("feedbackId") int feedbackId, @RequestBody Feedback feedback) {
//        try {
//            feedback.setId(feedbackId);
//            feedbackService.updateFeedback(feedback);
//            return new ResponseEntity(HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/{feedbackId}")
//    public ResponseEntity deleteFeedback(@PathVariable("feedbackId") int feedbackId) {
//        try {
//
//            Feedback feedback = new Feedback();
//            feedback.setId(feedbackId);
//            feedbackService.deleteFeedback(feedback);
//            return new ResponseEntity(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}