package container.code.data.repository.service.impl;

import container.code.data.entity.Account;
import container.code.data.repository.AccountRepository;
import container.code.data.repository.service.AccountService;
import container.code.function.account.UserMapper;
import container.code.function.account.api.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserMapper userMapper;
    private Account findAccount(Integer acc_id) {
        Account existAcc = accountRepository.findById(acc_id).orElseThrow(() -> new NotFoundException("Account not found"));
        return existAcc;
    }
    @Override
    public List<UserResponse> getAllAccount() {
        return accountRepository.findAll()
                .stream().map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void banUser(Integer id) {
        Account existAcc = findAccount(id);
        existAcc.setBanned(true);
        accountRepository.save(existAcc);
    }

}
