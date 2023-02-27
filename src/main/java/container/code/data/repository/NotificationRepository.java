package container.code.data.repository;

import container.code.data.entity.Account;
import container.code.data.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("SELECT t FROM Notification t")
    List<Notification> findAllNotification();
}
