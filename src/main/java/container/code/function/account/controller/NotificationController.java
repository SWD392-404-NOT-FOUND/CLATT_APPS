package container.code.function.account.controller;

import container.code.data.entity.Notification;
import container.code.function.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/notification")
public class NotificationController {
    @Autowired
    private AccountService accountService;

    @GetMapping("getAllNotification")
    public List<Notification> getAllNotification(){return accountService.getAllNotification();}
}
