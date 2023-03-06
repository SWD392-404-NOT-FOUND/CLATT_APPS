package container.code.data.repository.service;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.Account;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccount();

    Optional<Account> getAccountById(Integer Id);

    List<Account> getAccountByRole(String role);

    Account getAccountByEmail(String email);

    ResponseEntity<ResponseObject> banAccount(Integer id);

    ResponseEntity<ResponseObject> getAccountsForOrder(LocalDateTime date);
}
