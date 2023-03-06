package container.code.data.repository.service;

import container.code.data.entity.Account;
import container.code.function.account.api.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    List<UserResponse> getAllAccount();

    void banUser(Integer id);
}
