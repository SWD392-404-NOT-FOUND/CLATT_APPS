package container.code.function.feedback;

import container.code.data.entity.Feedback;
import container.code.function.feedback.api.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    List<FeedbackResponse> getFeedbacks(int employee_id, int job_id, Integer rate);

    boolean addFeedback(Integer id, Feedback feedback) throws IllegalAccessException;

    void updateFeedback(Feedback feedback);

    void deleteFeedback(Feedback feedback);
}