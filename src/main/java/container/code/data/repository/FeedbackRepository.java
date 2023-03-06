package container.code.data.repository;

import container.code.data.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
