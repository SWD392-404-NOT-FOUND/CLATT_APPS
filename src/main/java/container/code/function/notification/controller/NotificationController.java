package container.code.function.notification.controller;

import container.code.data.dto.NotificationRequestDto;
import container.code.data.dto.ResponseObject;
import container.code.data.dto.SubscriptionRequestDto;
import container.code.function.notification.service.FCMService;
import container.code.function.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getNotification")
    @PreAuthorize("hasAnyAuthority('admin', 'renter', 'employee')")
    public ResponseEntity<ResponseObject> getNotificationDetail(
            @RequestParam(name = "account_id") Integer accountId,
            @RequestParam(name = "notification_id") Integer notificationId) {
        return notificationService.getNotificationDetail(accountId, notificationId);
    }

    @GetMapping("/getAllNotifications")
    @PreAuthorize("hasAnyAuthority('admin', 'renter', 'employee')")
    public ResponseEntity<ResponseObject> getAllNotifications(
            @RequestParam(name = "account_id") Integer accountId) {
        return notificationService.getAllMyNotificationsById(accountId);
    }

    @Autowired
    private FCMService fcmService;
    @PostMapping("/subscribe")
    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        fcmService.subscribeToTopic(subscriptionRequestDto);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        fcmService.unsubscribeFromTopic(subscriptionRequestDto);
    }
    @PostMapping("/topic")
    public String sendPnsToTopic(@RequestBody NotificationRequestDto notificationRequestDto) {
        return fcmService.sendPnsToTopic(notificationRequestDto);
    }
}
