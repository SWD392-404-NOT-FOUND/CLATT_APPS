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

    @GetMapping("getFeedbackWithRate")
    @PreAuthorize("hasAnyAuthority('admin','renter','employee')")
    public ResponseEntity<ResponseObject> getFeedbackWithRate(
            @RequestParam(name = "account_id") Integer id,
            @RequestParam(name = "rate") Integer rate) {
        return feedbackService.getFeedbackWithRate(id, rate);
    }
}
