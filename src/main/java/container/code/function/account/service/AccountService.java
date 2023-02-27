package container.code.function.account.service;

import container.code.data.entity.Account;
import container.code.data.entity.Notification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();
    List<Notification>getAllNotification();
}
