package container.code.data.repository.service.impl;

import container.code.data.entity.Account;
import container.code.data.repository.AccountRepository;
import container.code.data.repository.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

}
