package container.code.data.repository.service;

import container.code.data.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();
}
