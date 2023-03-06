package container.code.function.notification.service.impl;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.BookingOrder;
import container.code.data.entity.Notification;
import container.code.data.repository.BookingOrderRepository;
import container.code.data.repository.NotificationRepository;
import container.code.function.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    @Override
    public ResponseEntity<ResponseObject> getNotificationDetail(Integer accountId, Integer notificationId) {
        Optional<Notification> notification = notificationRepository.findByIdAndAccountId(notificationId, accountId);

        if (notification.isPresent()) return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseObject(HttpStatus.FOUND.toString(),  null, notification));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.toString(),  "Can not find notification", null));
    }

    @Override
    public ResponseEntity<ResponseObject> getAllMyNotificationsById(Integer accountId) {
        List<Notification> listNotification = notificationRepository.findByAccountId(accountId);

        if (!listNotification.isEmpty()) return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseObject(HttpStatus.FOUND.toString(),  null, listNotification));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.toString(),  "Can not find notification", null));
    }
}
