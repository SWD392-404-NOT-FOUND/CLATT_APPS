package container.code.function.feedback.impl;

import container.code.data.entity.EmployeeOrder;
import container.code.data.entity.Feedback;
import container.code.data.repository.EmployeeOrderRepository;
import container.code.function.feedback.FeedbackMapper;
import container.code.function.feedback.api.FeedbackResponse;
import container.code.function.feedback.FeedbackService;
import container.code.data.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    EmployeeOrderRepository employeeOrderRepository;

    @Override
    public List<FeedbackResponse> getFeedbacks(int employee_id, int job_id, Integer rate) {
        List<FeedbackResponse> feedbacks = feedbackRepository.findAllByEmployeeOrder(employee_id, job_id, rate).stream().map(feedbackMapper::toFeedbackResponse).collect(Collectors.toList());
        return feedbacks;
    }

    private Feedback findFeedback(Integer id) {
        Feedback existingFeedback = feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException("Feedback not found"));
        return existingFeedback;
    }

    private EmployeeOrder findEmployeeOrder(Integer id) {
        EmployeeOrder employeeOrder = employeeOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee order not found"));
        return employeeOrder;
    }

    @Override
    public boolean addFeedback(Integer id, Feedback feedback) throws IllegalAccessException {
        boolean check = false;
        EmployeeOrder employeeOrder = findEmployeeOrder(id);

        if (employeeOrder.getFeedback().getId() != null) {
            feedback.setEmployeeOrder(employeeOrder);
            feedbackRepository.save(feedback);
            employeeOrder.setFeedback(feedback);
            employeeOrderRepository.save(employeeOrder);
            check = true;
        } else {
            throw new IllegalAccessException("Already have a feedback");
        }
        return check;
    }

    public void updateFeedback(Feedback feedback) {
        Feedback existingFeedback = findFeedback(feedback.getId());

        existingFeedback.setDetail(feedback.getDetail());
        existingFeedback.setRate(feedback.getRate());
        existingFeedback.setTimestamp(feedback.getTimestamp());

        feedbackRepository.save(existingFeedback);
    }

    public void deleteFeedback(Feedback feedback) {
        Feedback existingFeedback = findFeedback(feedback.getId());
        EmployeeOrder employeeOrder = findEmployeeOrder(existingFeedback.getEmployeeOrder().getId());
        employeeOrder.setFeedback(null);
        employeeOrderRepository.save(employeeOrder);
        feedbackRepository.delete(existingFeedback);
    }
}
