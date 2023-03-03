package container.code.function.account.service;

import container.code.data.dto.AccountDTO;
import container.code.data.dto.JobDTO;
import container.code.data.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountDTO> update(AccountDTO imageDTO);
    Page<AccountDTO> findAll(Pageable pageable);
    Optional<AccountDTO> findOne(Integer id);

}
