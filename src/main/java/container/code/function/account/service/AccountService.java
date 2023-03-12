package container.code.function.account.service;

import container.code.data.dto.ResponseObject;
import container.code.data.entity.Account;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    ResponseEntity<ResponseObject> getAllAccount();

    ResponseEntity<ResponseObject> getAccountById(Integer Id);

    ResponseEntity<ResponseObject> getAccountByRole(String role);

    ResponseEntity<ResponseObject> getAccountByEmail(String email);

    ResponseEntity<ResponseObject> banAccount(Integer id);

    ResponseEntity<ResponseObject> getAccountsForOrder(LocalDateTime date);
}
